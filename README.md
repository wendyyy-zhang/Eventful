================ Program Implementation ================

Objectives:
- design to follow Clean Architecture and SOLID principles
- implement design patterns (ex. Dependency injection, Factory, etc.)
- use Java interface and inheritance

===================== Instruction =====================

- Create an account before logging in
- Enter "back" to return to the previous level
- Enter "exit" to exit the program at anytime

===================== Program info =====================

Summary: Eventful is a program for holding events. 

Users and supported functionalities:
1. Attendee: attend and rate for events, send messages, add friend
2. Organizer: schedule, attend, and rate for events, send messages, add friend
3. Speaker: give talks (event), send messages

Specification and user access:
1. event-related:
- request for event list (Attendee, Organizer)
- sign up (Attendee, Organizer)
- cancel spot (Attendee, Organizer)
- schedule new event (Organizer)
- rate for events after attended (Attendee, Organizer)
2. message-related:
- see received/sent message (All)
- support unread/archive/delete/reply messages (All)
- message one user (Attendee, Organizer)
- message users of one type (Organizer, Speaker)
- message attendees of events (Speaker)
3. friend-related
- request for friend list (Attendee, Organizer)
- add friend (Attendee, Organizer)
