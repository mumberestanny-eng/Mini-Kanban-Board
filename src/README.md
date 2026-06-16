Mini KanBan Board.

A clean, responsive task management workspace built in Java Swing.
This application implements the foundational principles of agile workflows—modeled
after professional platforms like Jira and Trello—by managing the dynamic lifecycle
of tasks as they transition from inception to active development and final completion.

Core Architectural Features.

Bi-directional Task Transitions: Coordinates task object states across distinct logical phases,
implementing clean data transfer rules that prevent item loss or duplicate indexing references.

State-Driven Multi-Column UI: Uses an decoupled "Clear and Render" UI pattern.
Rather than manually updating labels row-by-row on a click event,
a centralized rendering loop wipes both columns clean and regenerates components directly from the updated data layer arrays.

Sleek, Production-Ready Layout: Implements an advanced layout layout structure utilizing a responsive GridLayout to guarantee uniform column scales,
alongside BoxLayout mechanics for consistent vertical stacking behavior.

Anti-Aliased Custom Components: Eliminates dated,
rigid native Swing aesthetics by incorporating custom vector painting loops for perfectly rounded borders,
anti-aliased inputs, and responsive, color-coded interactive control buttons.

Technical Deep-Dive: Data Routing & State Mechanics

The backbone of this application centers on predictable array modifications and sequential data flow.
It intentionally solves classic data pointer traps through structured array manipulations:
1. Straight Queue Control (First-In, First-Out Flow)
    The Concept: When a developer handles cards on a Kanban board, clicking a transition button should follow natural workflow queues.

    The Solution: The backend processes the active todoList collection using queue-like behavior (removing from index 0).
    This ensures tasks move sequentially from the very top of the backlog down to completion.
    Conversely, the doneList processes incoming data like a timeline stack,
    ensuring the most recently completed task is instantly accessible at the top of the finalized work pane.
2. Safeguarding Collection Limits
   The Concept: Attempting to pull elements out of an empty list when a button is clicked will immediately
   throw an unrecoverable IndexOutOfBoundsException and crash the desktop application.

    The Solution: The shifting methods implement explicit list boundary checks (!todoList.isEmpty() and !doneList.isEmpty()).
    If a column runs completely out of items, the button events fail gracefully,
    maintaining perfect system stability.

Component & File Architecture

The codebase cleanly decouples functional responsibilities to maintain scalability:

Task.java: The object-oriented data blueprint representing an encapsulated workflow entity.
MiniKanbanBoard.java: The core controller window handling operational button action listeners,
list state storage, layout management, and interface drawing.
RoundedButton.java: A custom JButton extension that completely bypasses rectangular native rendering to draw smooth,
anti-aliased curves that dynamically adjust tints based on hover and press gestures.
RoundedBorder.java: A custom AbstractBorder pipeline using anti-aliased strokes to apply rounded corners
to the column frames and the task-creation input panel.

⚙️ How To Run & Manage Workflows

Compile and Run: Launch the main execution line within MiniKanbanBoard.java.
Create a Task: Type your assignment description into the dark-mode input pane and press Create Task.
The card will instantiate and snap directly into the "To Do" section.
Advance Status: Click the green Complete Task → controller.
The oldest item at the top of your active list will pop out and smoothly slide over to the top of the "Done" panel.
Revert Status: Click the crimson ← Reverted to Do controller.
If a mistake was made, the most recently completed task will immediately pop out of the completion list and move back over to active development.