import random
days=["Monday", "Tuesday", "Wednesday", "Thursday", "Friday", "Saturday"]
time_slots =["9:00-10:00","10:00-11:00","11:00-12:00","1:00-2:00","2:00-3:00"]
subjects["Math", "Physics", "Compute Science", "AI", "Data Science"]
faculty=["Prof.A","Prof.B","Prof.C","Prof.D","Prof.E"]
timetable={}
for day in days:
	timetable[day]={}
	for slot in time_slots:
		subject=random.choice(subjects)
		professor=random.choice(faculty)
		timetable[day][slot]=f"{subject}-{professor}"
for day,slots in timetable.items():
	print(f"\n{day}:")
	for slot,details in slots.items():
		print(f"{slot}->{details}")

