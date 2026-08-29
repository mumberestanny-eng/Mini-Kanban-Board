# Mini-Kanban Board

A modern, lightweight desktop Kanban application built in Java Swing.
This repository represents a complete architectural refactoring of a legacy Java GUI project,
applying SOLID principles, clean modular code structure, and UI/UX design updates.

## Architectural & Technical Breakdown

### SOLID Principles Applied

    Single Responsibility Principle (SRP): Business logic is fully decoupled from the UI.
    The TaskEngine class manages task state transitions and list operations, while MiniKanbanBoard handles layout, styling, and UI events.
    
    Open/Closed Principle (OCP): UI component extension is achieved without altering existing Swing classes by 
    implementing reusable custom component wrappers.
    
    Dependency Inversion & Modern Java Standards: Data transfer relies on immutable Java record types (Task), 
    replacing raw string manipulation and boolean flag states with structured queue methods (removeFirst()).

### Custom UI Components & Overrides

Enhanced Swing Component Rendering
Standard Java Swing components feature sharp 90-degree edges by default. 
To create a modern, rounded interface aesthetic, core visual components were extended and customized:

    RoundedPanel: Overrides JPanel rendering routines (paintComponent) to draw smooth, 
    antialiased rounded container boxes with configurable arc diameters and borders for list panels.
    
    RoundedButton: Overrides JButton painting and focus boundary methods to produce rounded control buttons 
    (Create Task, Complete Task, Reverted to Do) with clean edge geometry.
    
    RoundedBorder: A custom AbstractBorder implementation that replaces legacy Swing etched borders (TitledBorder)
    with rounded outline borders for text fields and structural panels.

### UI/UX Modernization Highlights

    Refined Color System: Replaced default contrast colors with a dark/light UI palette:
    a soft window background (#F4F6F8), dark slate list containers (#2B2D31), primary action buttons (#1A80E5), and warning/revert accents (#E27435).
    
    Typography: Converted hardcoded serif fonts (Times New Roman) to modern system sans-serif typography (Segoe UI / SansSerif).
    
    Interactive Micro-interactions: Built a dynamic placeholder text experience in JTextField using custom FocusListener logic.

# Project Structure

    src/
    └── mainboard/
        ├── MiniKanbanBoard.java               # Main frame & UI layout construction
        └── boardengine/
            ├── Task.java                      # Record model for immutable task data
            ├── TaskEngine.java                # Core domain engine managing task state queues
            └── boardstyling/
                ├── RoundedBorder.java         # Custom rounded border painter
                ├── RoundedButton.java         # Custom rounded button component
                └── RoundedPanel.java          # Custom rounded panel container

## Setup & Execution
### Prerequisites
    Java Development Kit (JDK) 21 or higher installed.

### Compilation & Run via Terminal
1. Clone the repository:

       git clone https://github.com/mumberestanny-eng/Mini-Kanban-Board.git
       cd Mini-Kanban-Board

2. Compile source files:

       javac -d bin src/mainboard/*.java src/mainboard/boardengine/*.java src/mainboard/boardengine/boardstyling/*.java

3. Launch application:

       java -cp bin mainboard.MiniKanbanBoard
