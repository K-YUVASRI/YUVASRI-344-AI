<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Automated College Timetable Generator</title>
    <style>
        body {
            font-family: Arial, sans-serif;
            margin: 0;
            padding: 20px;
            background-color: #f5f5f5;
        }
        .container {
            max-width: 1200px;
            margin: 0 auto; 
            background: white;
            padding: 20px;
            border-radius: 8px;
            box-shadow: 0 0 10px rgba(0,0,0,0.1);
        }
        h1 {
            color: #2c3e50;
            text-align: center;
        }
        .tabs {
            display: flex;
            margin-bottom: 20px;
        }
        .tab {
            padding: 10px 20px;
            background: #eee;
            cursor: pointer;
            margin-right: 5px;
            border-radius: 5px 5px 0 0;
        }
        .tab.active {
            background: #3498db;
            color: white;
        }
        .tab-content {
            display: none;
            padding: 20px;
            border: 1px solid #ddd;
            border-radius: 0 0 5px 5px;
        }
        .tab-content.active {
            display: block;
        }
        table {
            width: 100%;
            border-collapse: collapse;
            margin-top: 20px;
        }
        th, td {
            border: 1px solid #ddd;
            padding: 8px;
            text-align: center;
        }
        th {
            background-color: #3498db;
            color: white;
        }
        tr:nth-child(even) {
             background-color: #f2f2f2;
        }
        .form-group {
            margin-bottom: 15px;
        }
        label {
            display: block;
            margin-bottom: 5px;
            font-weight: bold;
        }
        input, select {
            width: 100%;
            padding: 8px;
            border: 1px solid #ddd;
            border-radius: 4px;
            box-sizing: border-box;
        }
        button {
            background-color: #3498db;
            color: white;
            padding: 10px 15px;
            border: none;
            border-radius: 4px;
            cursor: pointer;
            font-size: 16px;
        }
        button:hover {
            background-color: #2980b9;
        }
        .success-message {
            color: green;
            margin-top: 10px;
        }
        .error-message {
            color: red;
            margin-top: 10px;
        }
    </style>
</head>
<body>
    <div class="container">
        <h1>Automated College Timetable Generator</h1>
        
        <div class="tabs">
            <div class="tab active" onclick="openTab('input')">Input Data</div>
            <div class="tab" onclick="openTab('generate')">Generate Timetable</div>
            <div class="tab" onclick="openTab('view')">View Timetable</div>
        </div>
        
        <div id="input" class="tab-content active">
            <h2>Input Data</h2>
            
            <div class="form-group">
                <label for="course">Course Name:</label>
                <input type="text" id="course" placeholder="e.g., Computer Science 101">
            </div>
            
            <div class="form-group">
                <label for="teacher">Teacher:</label>
                <input type="text" id="teacher" placeholder="e.g., Dr. Smith">
            </div>
            
            <div class="form-group">
                <label for="room">Room:</label>
                <input type="text" id="room" placeholder="e.g., Room 205">
            </div>
            
            <div class="form-group">
                <label for="batch">Batch/Class:</label>
                <input type="text" id="batch" placeholder="e.g., CS-2023">
            </div>
            
            <div class="form-group">
                <label for="duration">Duration (hours):</label>
                <input type="number" id="duration" min="1" max="4" value="1">
            </div>
            
            <div class="form-group">
                <label>Available Days:</label>
                <div>
                    <input type="checkbox" id="monday" checked>
                    <label for="monday" style="display: inline;">Monday</label>
                </div>
                <div>
                    <input type="checkbox" id="tuesday" checked>
                    <label for="tuesday" style="display: inline;">Tuesday</label>
                </div>
                <div>
                    <input type="checkbox" id="wednesday" checked>
                    <label for="wednesday" style="display: inline;">Wednesday</label>
                </div>
                <div>
                    <input type="checkbox" id="thursday" checked>
                    <label for="thursday" style="display: inline;">Thursday</label>
                </div>
                <div>
                    <input type="checkbox" id="friday" checked>
                    <label for="friday" style="display: inline;">Friday</label>
                </div>
            </div>
            
            <button onclick="addCourse()">Add Course</button>
            <div id="input-message" class="success-message"></div>
            
            <h3>Current Courses</h3>
            <table id="courses-table">
                <thead>
                    <tr>
                        <th>Course</th>
                        <th>Teacher</th>
                        <th>Room</th>
                        <th>Batch</th>
                        <th>Duration</th>
                        <th>Days</th>
                    </tr>
                </thead>
                <tbody>
                    <!-- Courses will be added here dynamically -->
                </tbody>
            </table>
        </div>
        
        <div id="generate" class="tab-content">
            <h2>Generate Timetable</h2>
            <p>Click the button below to generate a timetable based on the input data.</p>
            <button onclick="generateTimetable()">Generate Timetable</button>
            <div id="generate-message"></div>
        </div>
        
        <div id="view" class="tab-content">
            <h2>Generated Timetable</h2>
            <div id="timetable-container">
                <p>No timetable generated yet. Please go to the "Generate Timetable" tab.</p>
            </div>
        </div>
    </div>

    <script>
        // Sample data storage
        let courses = [];
        
        // Tab navigation
        function openTab(tabName) {
            const tabs = document.getElementsByClassName("tab");
            const tabContents = document.getElementsByClassName("tab-content");
            
            for (let i = 0; i < tabs.length; i++) {
                tabs[i].classList.remove("active");
                tabContents[i].classList.remove("active");
            }
            
            document.getElementById(tabName).classList.add("active");
            event.currentTarget.classList.add("active");
        }
        
        // Add a new course
        function addCourse() {
            const course = document.getElementById("course").value;
            const teacher = document.getElementById("teacher").value;
            const room = document.getElementById("room").value;
            const batch = document.getElementById("batch").value;
            const duration = document.getElementById("duration").value;
            
            // Get selected days
            const days = [];
            if (document.getElementById("monday").checked) days.push("Mon");
            if (document.getElementById("tuesday").checked) days.push("Tue");
            if (document.getElementById("wednesday").checked) days.push("Wed");
            if (document.getElementById("thursday").checked) days.push("Thu");
            if (document.getElementById("friday").checked) days.push("Fri");
            
            if (!course || !teacher || !room || !batch || !duration || days.length === 0) {
                document.getElementById("input-message").textContent = "Please fill all fields!";
                document.getElementById("input-message").className = "error-message";
                return;
            }
            
            const newCourse = {
                course,
                teacher,
                room,
                batch,
                duration,
                days: days.join(", ")
            };
            
            courses.push(newCourse);
            updateCoursesTable();
            
            // Clear form
            document.getElementById("course").value = "";
            document.getElementById("teacher").value = "";
            document.getElementById("room").value = "";
            document.getElementById("batch").value = "";
            document.getElementById("duration").value = "1";
            
            document.getElementById("input-message").textContent = "Course added successfully!";
            document.getElementById("input-message").className = "success-message";
        }
        
        // Update the courses table
        function updateCoursesTable() {
            const tableBody = document.querySelector("#courses-table tbody");
            tableBody.innerHTML = "";
            
            courses.forEach(course => {
                const row = document.createElement("tr");
                
                row.innerHTML = `
                    <td>${course.course}</td>
                    <td>${course.teacher}</td>
                    <td>${course.room}</td>
                    <td>${course.batch}</td>
                    <td>${course.duration} hour(s)</td>
                    <td>${course.days}</td>
                `;
                
                tableBody.appendChild(row);
            });
        }
        
        // Generate timetable (simplified version)
        function generateTimetable() {
            if (courses.length === 0) {
                document.getElementById("generate-message").textContent = "Please add at least one course first!";
                document.getElementById("generate-message").className = "error-message";
                return;
            }
            
            // In a real system, this would use an algorithm to schedule without conflicts
            // For this prototype, we'll just display a sample timetable
            
            const timetableHTML = `
                <h3>Sample Generated Timetable</h3>
                <table>
                    <thead>
                        <tr>
                            <th>Time</th>
                            <th>Monday</th>
                            <th>Tuesday</th>
                            <th>Wednesday</th>
                            <th>Thursday</th>
                            <th>Friday</th>
                        </tr>
                    </thead>
                    <tbody>
                        <tr>
                            <td>9:00-10:00</td>
                            <td>${courses[0].course}<br>${courses[0].teacher}<br>${courses[0].room}</td>
                            <td>${courses.length > 1 ? courses[1].course+'<br>'+courses[1].teacher+'<br>'+courses[1].room : '-'}</td>
                            <td>${courses.length > 2 ? courses[2].course+'<br>'+courses[2].teacher+'<br>'+courses[2].room : '-'}</td>
                            <td>${courses.length > 3 ? courses[3].course+'<br>'+courses[3].teacher+'<br>'+courses[3].room : '-'}</td>
                            <td>${courses.length > 4 ? courses[4].course+'<br>'+courses[4].teacher+'<br>'+courses[4].room : '-'}</td>
                        </tr>
                        <tr>
                            <td>10:00-11:00</td>
                            <td>${courses.length > 5 ? courses[5].course+'<br>'+courses[5].teacher+'<br>'+courses[5].room : '-'}</td>
                            <td>${courses.length > 6 ? courses[6].course+'<br>'+courses[6].teacher+'<br>'+courses[6].room : '-'}</td>
                            <td>${courses.length > 7 ? courses[7].course+'<br>'+courses[7].teacher+'<br>'+courses[7].room : '-'}</td>
                            <td>${courses.length > 8 ? courses[8].course+'<br>'+courses[8].teacher+'<br>'+courses[8].room : '-'}</td>
                            <td>${courses.length > 9 ? courses[9].course+'<br>'+courses[9].teacher+'<br>'+courses[9].room : '-'}</td>
                        </tr>
                    </tbody>
                </table>
                <p><strong>Note:</strong> This is a simplified display. A real system would use an algorithm to avoid 
                scheduling conflicts.</p>
            `;
            
            document.getElementById("timetable-container").innerHTML = timetableHTML;
            document.getElementById("generate-message").textContent = "Timetable generated successfully!";
            document.getElementById("generate-message").className = "success-message";
            
            // Switch to view tab
            openTab('view');
            document.querySelector('.tab[onclick="openTab(\'view\')"]').classList.add("active");
        }
    </script>
</body>
</html>
