// LoginForm.js
import { useState } from 'react';
import FormWrapper from '../Elements/FormWrapper';
import TextInput from '../Elements/TextInput';

const LoginForm = ({ onLogin }) => {
    const [email, setEmail] = useState('');
    const [password, setPassword] = useState('');

    const handleSubmit = (e) => {
        e.preventDefault();
        onLogin({ email, password });
    };

    return (
        <FormWrapper title="Login" onSubmit={handleSubmit}>
            <TextInput label="Email" name="email" value={email} onChange={(e) => setEmail(e.target.value)} />
            <TextInput label="Password" name="password" type="password" value={password} onChange={(e) => setPassword(e.target.value)} />
            <button type="submit">Login</button>
        </FormWrapper>
    );
};

export default LoginForm;
