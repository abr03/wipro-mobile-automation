# Mobile Automation Framework (Wipro Assessment Submission)

This repository contains a modular, maintainable, single-codebase mobile automation framework built with Java, Appium, and TestNG. The design addresses complex UI synchronization constraints and cross-platform architecture requirements without relying on code duplication.

---

##  Framework Structure & Key Decisions

### 1. Unified Cross-Platform Strategy (Android & iOS)
To maximize code reuse, the framework utilizes a **Single-Class Page Object Model (POM)** pattern instead of splitting test classes by platform. 
- Elements are maintained via dual dictionary maps (`androidXpath` and `iosXpath`) loaded directly into the class constructor.
- Test methods pull dynamic keys on the fly, ensuring business flows remain identical and completely decoupled from underlying platform differences.

### 2. Synchronization & Flakiness Mitigations
- **Zero Thread.sleep():** High-stability implicit and explicit waiting conditions (`isVisible` / `waitExplicitlyForElement`) track visibility and clickability dynamically.
- **Dynamic State-Change Handling:** In iOS (XCUITest), focusing an element (e.g., launching the virtual keyboard) alters the DOM tree, frequently throwing a `StaleElementReferenceException` or causing duplicated text entries. The framework gracefully handles this using an explicit **Click ➔ Re-find ➔ Clear ➔ Type** execution strategy to securely lock onto the updated UI node exactly when the platform tree updates.

---

## 🛠️ Prerequisites & Setup

Ensure the following items are configured in your local environment path:
- **Java Development Kit (JDK 17+)**
- **Apache Maven 3.8+**
- **Appium Server 2.x** installed globally via Node Package Manager: `npm install -g appium`
- **Appium Active Drivers:**
  - Android: `appium driver install uiautomator2`
  - iOS (Requires macOS): `appium driver install xcuitest`

### Application Under Test (AUT) Source
The tests target the **Lime Demo App v1.0.0**. Download the application binaries directly from the official source:
👉 **[Download Link: Lime Demo App Releases](https://github.com/taqelah/demo-app/releases/tag/v1.0.0)**

Save your downloaded binaries inside your local project repository directory:
- Android Path: `src/main/resources/DemoApp-v1.0.0.apk`
- iOS Path: `src/main/resources/DemoApp-v1.0.0.app` (Unzipped Bundle Folder)

---

## 🚀 How To Run The Tests

Before launching any execution suite, start your local Appium listener service on your machine:
```bash

appium -p 4723
Selecting Your Platform (testng.xml)
Before clicking run, you must specify your target platform inside your testng.xml configuration file at the root of the project. Locate the platform parameter line:

To Run iOS: Update the value parameter to "ios":
<parameter name="platform" value="ios"/>

To Run Android: Update the value parameter to "android":

<parameter name="platform" value="android"/>

Once your parameter is updated, right-click your testng.xml file in your IDE and select Run to launch your tests.

🤖 How and Where AI Was Used

XPath Synchronization: Used to evaluate complex, raw native XML layouts and formulate highly resilient structural cross-platform
