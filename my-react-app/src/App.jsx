import { useState } from "react";
import "./style.css";

function App() {
  const [name, setName] = useState("");
  const [email, setEmail] = useState("");
  const [password, setPassword] = useState("");
  const [savedData, setSavedData] = useState(null);

  function handleSubmit(e) {
    e.preventDefault();

    const data = {
      name: name,
      email: email,
      password: password
    };

    localStorage.setItem("formData", JSON.stringify(data));
    setSavedData(data);

    setName("");
    setEmail("");
    setPassword("");
  }

  return (
    <div className="page">
      <div className="form-box">
        <h2>Create Account</h2>
        <p className="subtitle">Fill the details below</p>

        <form onSubmit={handleSubmit}>
          <div className="input-group">
            <label>Name</label>
            <input
              type="text"
              placeholder="Enter your name"
              value={name}
              onChange={e => setName(e.target.value)}
              required
            />
          </div>

          <div className="input-group">
            <label>Email</label>
            <input
              type="email"
              placeholder="Enter your email"
              value={email}
              onChange={e => setEmail(e.target.value)}
              required
            />
          </div>

          <div className="input-group">
            <label>Password</label>
            <input
              type="password"
              placeholder="Enter your password"
              value={password}
              onChange={e => setPassword(e.target.value)}
              required
            />
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
