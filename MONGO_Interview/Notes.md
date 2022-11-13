#Notes about implementation:

- For printing the contents in the scanned sequence, I have used a tree map that will memorize the exact order of all the items. This will have in theory the exact same complexity as the previous implementation.
- For printing the total, a couple of lines were added. What makes the difference is that, after obtaining a price you add it to the total and after going through the cart you print the total in the already used format.
- While there might be a better way to implement the reformatting of the printing method, I have opted for a simple code represented as an integer that will be checked whenever the method ```printReceipt``` will add another line to the console. For scalability, whenever a new format appears, a new ```if-else``` statement will be added inside this method.
- I have added another product to the database in order to make sure that the format will display correctly the cents of the prices.