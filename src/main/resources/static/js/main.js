'use strict';

// DOM elements
var usernamePage = document.querySelector('#username-page');
var chatPage = document.querySelector('#chat-page');
var usernameForm = document.querySelector('#usernameForm');
var messageForm = document.querySelector('#messageForm');
var messageInput = document.querySelector('#message');
var messageArea = document.querySelector('#messageArea');
var connectingElement = document.querySelector('.connecting');

// WebSocket client and user information
var stompClient = null;
var username = null;

// Avatar colors for users
var colors = [
    '#2196F3', '#32c787', '#00BCD4', '#ff5652',
    '#ffc107', '#ff85af', '#FF9800', '#39bbb0'
];

// Function to connect to the WebSocket server
function connect(event) {
    event.preventDefault();
    // Get the username entered by the user
    username = document.querySelector('#name').value.trim();

    // Check if a username is entered
    if (!username) {
        alert('Please enter a username.');
        return;
    }

    // Hide the username page and show the chat page
    usernamePage.classList.add('hidden');
    chatPage.classList.remove('hidden');

    // Establish WebSocket connection
    var socket = new SockJS('/websocket');
    stompClient = Stomp.over(socket);

    // Connect to the WebSocket server
    stompClient.connect({}, onConnected, onError);
}

// Function called when WebSocket connection is established
function onConnected() {
    // Subscribe to the public topic to receive messages
    stompClient.subscribe('/topic/public', onMessageReceived);

    // Send a message to the server to register the username
    stompClient.send("/app/chat.register",
        {},
        JSON.stringify({ sender: username, type: 'JOIN' })
    )

    // Hide the connecting element
    connectingElement.classList.add('hidden');
}

// Function called on WebSocket connection error
function onError(error) {
    // Display error message to the user
    connectingElement.textContent = 'Failed to connect to WebSocket! Please refresh the page and try again or contact the administrator.';
    connectingElement.style.color = 'red';
}

// Function to send a message
function send(event) {
    event.preventDefault();
    // Get the message content entered by the user
    var messageContent = messageInput.value.trim();

    // Check if message content is empty
    if (!messageContent) {
        alert('Please enter a message.');
        return;
    }

    // Check if connected to the chat
    if (!stompClient) {
        alert('You are not connected to the chat.');
        return;
    }

    // Create a chat message object
    var chatMessage = {
        sender: username,
        content: messageInput.value,
        type: 'CHAT'
    };

    // Send the message to the server
    stompClient.send("/app/chat.send", {}, JSON.stringify(chatMessage));
    // Clear the message input field
    messageInput.value = '';
}

// Function called when a message is received
function onMessageReceived(payload) {
    // Parse the message received from the server
    var message = JSON.parse(payload.body);

    // Create a new list element for the message
    var messageElement = document.createElement('li');

    // Check the type of message
    if (message.type === 'JOIN') {
        // If it's a join message, add event class and set content
        messageElement.classList.add('event-message');
        message.content = message.sender + ' joined!';
    } else if (message.type === 'LEAVE') {
        // If it's a leave message, add event class and set content
        messageElement.classList.add('event-message');
        message.content = message.sender + ' left!';
    } else {
        // If it's a chat message, add chat class
        messageElement.classList.add('chat-message');

        // Create an avatar element for the sender
        var avatarElement = document.createElement('i');
        var avatarText = document.createTextNode(message.sender[0]);
        avatarElement.appendChild(avatarText);
        avatarElement.style['background-color'] = getAvatarColor(message.sender);

        // Append the avatar element to the message element
        messageElement.appendChild(avatarElement);

        // Create a username element for the sender
        var usernameElement = document.createElement('span');
        var usernameText = document.createTextNode(message.sender);
        usernameElement.appendChild(usernameText);

        // Append the username element to the message element
        messageElement.appendChild(usernameElement);
    }

    // Create a text element for the message content
    var textElement = document.createElement('p');
    var messageText = document.createTextNode(message.content);
    textElement.appendChild(messageText);

    // Append the text element to the message element
    messageElement.appendChild(textElement);

    // Append the message element to the message area
    messageArea.appendChild(messageElement);
    // Scroll to the bottom of the message area
    messageArea.scrollTop = messageArea.scrollHeight;
}

// Function to generate an avatar color based on the username
function getAvatarColor(messageSender) {
    var hash = 0;
    for (var i = 0; i < messageSender.length; i++) {
        hash = 31 * hash + messageSender.charCodeAt(i);
    }

    var index = Math.abs(hash % colors.length);
    return colors[index];
}

// Event listener for the username form submission
usernameForm.addEventListener('submit', connect, true);
// Event listener for the message form submission
messageForm.addEventListener('submit', send, true);
