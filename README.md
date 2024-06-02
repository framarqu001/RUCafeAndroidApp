# RU Café

RU Café is an Android application that simulates a comprehensive cafe ordering system, designed to enhance the user experience for both customers and cafe staff. With this app, users can effortlessly order a variety of items, including sandwiches, donuts, and coffee, directly from their mobile devices.

## Features
- **Ordering Donuts:** Choose from yeast donuts, cake donuts, and donut holes with various flavors and quantities.
- **Ordering Coffee:** Customize your coffee with different cup sizes and multiple add-ins.
- **Ordering Sandwiches:** Select the type of bread, protein, and add-ons for your sandwich.
- **Current Order Management:** View, add, remove, and place orders.
- **Order History:** View all placed orders, including details and total amounts, and the ability to cancel orders.

## Usage
1. Launch the RU Café app on your device.
2. Navigate through the different sections to place an order:
   - **Donuts:** Select the type, flavor, and quantity.
   - **Coffee:** Choose the cup size and add-ins.
   - **Sandwiches:** Customize your sandwich with bread, protein, and add-ons.
3. Review your current order, make adjustments if necessary, and place the order.
4. View all orders in the order history section, where you can also cancel any orders if needed.

## Screenshots

<img src="https://github.com/framarqu001/RUCafeAndroidApp/assets/119390184/d7266667-633f-421a-80dd-38daf5b4bfd1" alt="HomePage" width="300" style="margin-right: 20px; margin-bottom: 20px;"/>

<img src="https://github.com/framarqu001/RUCafeAndroidApp/assets/119390184/d8fc33de-5d5e-42de-b3ab-51735af63c51" alt="Donuts" width="300" style="margin-right: 20px; margin-bottom: 20px;"/>

<img src="https://github.com/framarqu001/RUCafeAndroidApp/assets/119390184/fca30954-6e6e-4bcf-955e-e97eaa61ce74" alt="Order" width="300" style="margin-right: 20px; margin-bottom: 20px;"/>



## Installation
1. Clone the repository:
    ```sh
    git clone https://github.com/yourusername/RUCafe.git
    ```
2. Open the project in Android Studio.
3. Build the project to download all dependencies.
4. Run the application on an emulator or a physical device.


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
