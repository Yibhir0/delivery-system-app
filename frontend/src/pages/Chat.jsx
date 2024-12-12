import React from 'react';

import ChatBot from '../components/UI/chat-bot/ChatBot.jsx';

import Helmet from "../components/Helmet/Helmet.jsx";

const Chat = () => {

    return (
        <Helmet title="Habibi">
            <ChatBot />
        </Helmet>
    );
}

export default Chat;


