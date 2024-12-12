// FormWrapper.js
import React from 'react';

import '../../styles/form-style.css'

const FormWrapper = ({ children, title, onSubmit }) => (
    <form onSubmit={onSubmit} className="form-wrapper">
        <h2>{title}</h2>
        {children}
    </form>
);

export default FormWrapper;
