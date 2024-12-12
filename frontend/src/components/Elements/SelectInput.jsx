
import React from 'react';

const SelectInput = ({ label, name, value, onChange, options = [] }) => (
    <div className="input-group">
        <label>{label}</label>
        <select name={name} value={value} onChange={onChange} required>
            <option value="" disabled></option>
            {options.map((option) => (
                <option key={option.value} value={option.value}>
                    {option.value}
                </option>
            ))}
        </select>
    </div>
);

export default SelectInput;
