# RU Café

RU Café is an Android application that simulates a cafe ordering system, allowing users to order items such as sandwiches, donuts, and coffee. This project was originally designed for a JavaFX application and has been implemented in Android Studio.

## Table of Contents
- [Features](#features)
- [Screenshots](#screenshots)
- [Installation](#installation)
- [Usage](#usage)
- [Architecture](#architecture)
- [Testing](#testing)
- [License](#license)

## Features
- **Ordering Donuts:** Choose from yeast donuts, cake donuts, and donut holes with various flavors and quantities.
- **Ordering Coffee:** Customize your coffee with different cup sizes and multiple add-ins.
- **Ordering Sandwiches:** Select the type of bread, protein, and add-ons for your sandwich.
- **Current Order Management:** View, add, remove, and place orders.
- **Order History:** View all placed orders, including details and total amounts, and the ability to cancel orders.

## Screenshots
![Ordering Donuts](path/to/donut_screenshot.png)
![Ordering Coffee](path/to/coffee_screenshot.png)
![Ordering Sandwiches](path/to/sandwich_screenshot.png)
![Order Summary](path/to/order_summary_screenshot.png)

## Installation
1. Clone the repository:
    ```sh
    git clone https://github.com/yourusername/RUCafe.git
    ```
2. Open the project in Android Studio.
3. Build the project to download all dependencies.
4. Run the application on an emulator or a physical device.

## Usage
1. Launch the RU Café app on your device.
2. Navigate through the different sections to place an order:
   - **Donuts:** Select the type, flavor, and quantity.
   - **Coffee:** Choose the cup size and add-ins.
   - **Sandwiches:** Customize your sandwich with bread, protein, and add-ons.
3. Review your current order, make adjustments if necessary, and place the order.
4. View all orders in the order history section, where you can also cancel any orders if needed.

## Architecture
The application follows the Model-View-Controller (MVC) design pattern:
- **Model:** Classes representing the menu items (Donut, Coffee, Sandwich) and the Order.
- **View:** XML layout files for different screens and user interfaces.
- **Controller:** Java classes handling user interactions and updating the views.

## Testing
- JUnit tests are implemented for the Sandwich class to ensure the price calculation is accurate.
- Manual testing was performed for all functionalities to ensure the application runs smoothly and meets the project requirements.

## License
This project is licensed under the MIT License - see the [LICENSE](LICENSE) file for details.
