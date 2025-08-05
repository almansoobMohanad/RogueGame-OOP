# Java Roguelike Game

> A terminal-based roguelike game built in Java as part of a university group project  
> Includes a Gemini API-powered dialogue system, dynamic NPCs, day/night mechanics, and more

<img width="2391" height="1136" alt="image" src="https://github.com/user-attachments/assets/97efeadb-3bf7-4907-83f5-935374e30d34" />
<img width="2366" height="1236" alt="image" src="https://github.com/user-attachments/assets/85988831-1bf1-4601-b4b5-c8ca55cd7b01" />



## Project Overview

This is a **roguelike game** developed in Java using a legacy codebase provided by the university. While core systems such as player movement were already part of the engine, our team designed and implemented additional gameplay features and systems.

The project emphasizes object-oriented design and applies SOLID principles throughout the codebase.

## Features

- Gemini API-powered **AI dialogue system**
- Multiple NPC types, including:
  - Merchants with a functional buying/trading system
  - Hostile creatures (e.g., zombies that appear at night)
- **Day/night cycle** affecting gameplay (zombies appear only at night)
- **Item system** including picking up and planting seeds
- **Combat system** to fight creatures
- Inventory and gold tracking system

## What We Learned

This project helped us:

- Apply **Object-Oriented Programming (OOP)** concepts in practice
- Design scalable systems using **SOLID principles**
- Work collaboratively in a **team of 4** using Git
- Integrate external services (e.g., **Gemini API**) into a Java-based application

## Team Members

- Mohanad AL-Mansoob
- Arielle Dela Cruz
- Adji Ilhamhafiz Sarie Hakim
- Anfal Muhammad Ahsan

## Project Status

- Core gameplay features implemented
- Dialogue system integrated with Gemini API
- Systems like merchants, combat, planting, and inventory are complete
- Potential for future polish and extension

## How to Run

1. Ensure you have **Java 17 or higher** installed.
2. Set your Gemini API key as an environment variable:
   - On macOS/Linux:
     ```bash
     export GEMINI_API_KEY=your_api_key_here
     ```
   - On Windows (Command Prompt):
     ```cmd
     set GEMINI_API_KEY=your_api_key_here
     ```
3. Clone the repository and compile the code:
   ```bash
   javac Main.java
   java Main
