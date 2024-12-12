import { useState, useEffect } from 'react';
import styled from 'styled-components';
import { askChatBot } from '../../../api/post/PostDataService';

const ChatBotContainer = styled.div`
    width: 60%;
    height: 80vh;
    margin: 0;
    border: none;
    display: flex;
    flex-direction: column;
    overflow: hidden;
    font-family: Arial, sans-serif;
    background-color: #f7f7f7;
    margin: 0 auto;
      border-radius: 12px;
`;

const ChatDisplay = styled.div`
    flex: 1;
    padding: 10px;
    background-color: #f0f0f0;
    overflow-y: auto;
    display: flex;
    flex-direction: column;
`;

const Message = styled.div`
    max-width: 70%;
    padding: 8px;
    margin: 6px 0;
    border-radius: 12px;
    line-height: 1.4;
    ${({ isUser }) =>
        isUser
            ? `
        background-color: #F56543;
        color: white;
        align-self: flex-end;
    `
            : `
        background-color: #e5e5e5;
        color: black;
        align-self: flex-start;
    `}
`;

const ChatInputContainer = styled.div`
    padding: 10px;
    border-top: 1px solid #ddd;
    display: flex;
    align-items: center;
`;

const ChatInput = styled.input`
    flex: 1;
    padding: 8px;
    border: 1px solid #ccc;
    border-radius: 20px;
    outline: none;
    font-size: 14px;
`;

const SendButton = styled.button`
    padding: 8px 12px;
    margin-left: 8px;
    border: none;
    background-color: #F56543;
    color: white;
    border-radius: 20px;
    cursor: pointer;
    font-size: 14px;
    &:hover {
        background-color: #D94E33;
    }
`;

const ChatBot = () => {
    const [messages, setMessages] = useState([]);
    const [input, setInput] = useState('');

    useEffect(() => {
        // Add initial "Hi" message when component mounts
        const initialMessage = { text: 'Hi, How can I help you Today', isUser: false };
        setMessages([initialMessage]);
    }, []);

    const handleSendMessage = async () => {
        if (input.trim()) {
            const newMessages = [...messages, { text: input, isUser: true }];
            setMessages(newMessages);
            setInput('');

            const data = await askChatBot(input);

            setTimeout(() => {
                // Simulate bot response
                const botResponse = { text: data.response, isUser: false };
                setMessages([...newMessages, botResponse]);
            }, 1000);
        }
    };

    const handleInputChange = (e) => {
        setInput(e.target.value);
    };

    const handleKeyPress = (e) => {
        if (e.key === 'Enter') {
            handleSendMessage();
        }
    };

    return (
        <ChatBotContainer>
            <ChatDisplay>
                {messages.map((message, index) => (
                    <Message key={index} isUser={message.isUser}>
                        {message.text}
                    </Message>
                ))}
            </ChatDisplay>
            <ChatInputContainer>
                <ChatInput
                    type="text"
                    placeholder="Type a message..."
                    value={input}
                    onChange={handleInputChange}
                    onKeyPress={handleKeyPress}
                />
                <SendButton onClick={handleSendMessage}>Send</SendButton>
            </ChatInputContainer>
        </ChatBotContainer>
    );
};

export default ChatBot;
