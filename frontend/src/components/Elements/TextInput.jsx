// TextInput.js
import React from 'react';

const TextInput = ({ label, name, value, onChange, type = "text" }) => (
    <div className="input-group">
        <label>{label}</label>
        <input
            type={type}
            name={name}
            value={value}
            onChange={onChange}
            required
        />
    </div>
);

export default TextInput;
