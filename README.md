## Installation

1. Open the project in Android Studio.

2. Ensure you have a PHP server running with the necessary database setup. Update the URLs in the code to match your server's IP address.

3. Run the PHP server and ensure the following endpoints are available:
   - `http://<your-server-ip>/hello.php`
   - `http://<your-server-ip>/newbook.php`
   - `http://<your-server-ip>/checkdue.php`

4. Connect your Android device or start an emulator.

5. Run the Android application from Android Studio.

## Usage

- Launch the app on your Android device.
- Use the provided buttons to:
  - List all books.
  - Lend a book by entering the required details.
  - Add new books.
  - Check for overdue books by entering start and end dates.

## Code Structure

- **MainActivity.kt**: The main activity that handles user interactions and communicates with the PHP backend.
- **activity_main.xml**: The layout file for the main activity containing buttons and input fields.
- **PHP Files**: Handle the requests and responses between the Android app and the MySQL database.

## Technical Details

- The app uses HTTP GET requests to interact with the PHP server.
- Responses are processed as JSON and displayed in a formatted table within the app.
- User input is sanitized and encoded to prevent issues with special characters.

## Future Improvements

- Implement user authentication for borrowing books.
- Enhance the UI for better user experience.
- Add more features such as searching for books or filtering by category.
- Improve error handling and provide user feedback on actions.

## Contributing

Contributions are welcome! If you have suggestions or improvements, feel free to open an issue or submit a pull request.

## License

This project is licensed under the MIT License - see the LICENSE file for details.
