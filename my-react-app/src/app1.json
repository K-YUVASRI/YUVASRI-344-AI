import { useState } from "react";
import "./style.css";

function App() {
  const [name, setName] = useState("");
  const [email, setEmail] = useState("");
  const [password, setPassword] = useState("");
  const [savedData, setSavedData] = useState(null);
  const [errors, setErrors] = useState({});

   function validate() {
    const newErrors = {};

    if (!name.trim()) {
      newErrors.name = "Name is required";
    }

    if (!/^[^\s@]+@[^\s@]+\.[^\s@]+$/.test(email)) {
      newErrors.email = "Enter a valid email";
    }

    if (!/^(?=.*[a-z])(?=.*[A-Z])(?=.*\d).{8,}$/.test(password)) {
      newErrors.password =
        "Min 8 chars, include uppercase, lowercase & number";
    }

    setErrors(newErrors);
    return Object.keys(newErrors).length === 0;
  }
 
  function handleSubmit(e) {
    e.preventDefault();

    if (!validate()) return;

    const data = { name, email, password };

   localStorage.setItem("formData", JSON.stringify(data));
    setSavedData(data);
    alert("saved");

    setName("");
    setEmail("");
    setPassword("");
    setErrors({});
  }

  return (
    <div className="page">
      <div className="form-box">
        <h2>Create Account</h2>
        <p className="subtitle">Fill the details below</p>

        <form onSubmit={handleSubmit} noValidate>
          <div className="input-group">
            <label>Name</label>
            <input
              type="text"
              placeholder="Enter your name"
              value={name}
              onChange={(e) => setName(e.target.value)}
            />
            {errors.name && <small>{errors.name}</small>}
          </div>

          <div className="input-group">
            <label>Email</label>
            <input
              type="email"
              placeholder="Enter your email"
              value={email}
              onChange={(e) => setEmail(e.target.value)}
            />
            {errors.email && <small>{errors.email}</small>}
          </div>

          <div className="input-group">
            <label>Password</label>
            <input
              type="password"
              placeholder="Enter your password"
              value={password}
              onChange={(e) => setPassword(e.target.value)}
            />
            {errors.password && <small>{errors.password}</small>}
          </div>

          <button type="submit">Save</button>
        </form>

        {savedData && (
          <div className="output">
            <h3>Saved Output</h3>
            <p><b>Name:</b> {savedData.name}</p>
            <p><b>Email:</b> {savedData.email}</p>
            <p><b>Password:</b> {savedData.password}</p>
          </div>
        )}
      </div>
    </div>
  );
}

export default App;
