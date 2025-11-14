# VeryDeliSandwiches

VeryDeliSandwiches is a console-based point-of-sale application for **Very-Deli Sandwiches**,
a custom sandwich shop. Customers can build sandwiches, add drinks and chips,
see a detailed order summary, and generate a receipt file for each order.

---

## 1. Project Overview

Goals:

- Practice **Object-Oriented Analysis and Design** (OOAD)
- Model a real sandwich shop using Java classes
- Separate **UI** and **business logic**
- Persist each completed order as a **receipt file** in a `receipts` folder

Main responsibilities:

- `UserInterface` handles all menus, prompts, and console interaction.
- `Order`, `Sandwich`, `Drink`, and `Chips` model the domain.
- `PriceService` centralizes all pricing rules.
- The application guides the customer through creating one order at a time.

---

## 2. Features vs Capstone Requirements

### Ordering Flow

- Customer can place an order with **0 or more sandwiches**.
- If an order has **0 sandwiches**, the customer must buy **at least one drink or chips**.
- Customer can:
    - Add **sandwiches**
    - Add **drinks**
    - Add **chips**
    - See a detailed **checkout** screen
    - Confirm or cancel the order

### Sandwich Builder

For each sandwich the user can:

- Choose **bread**: `white`, `wheat`, `rye`, `wrap`
- Choose **size**: `4"`, `8"`, `12"`
- Add toppings by category:
    - **Meats** (premium): steak, ham, salami, roast beef, chicken, bacon
    - **Cheeses** (premium): American, Provolone, Cheddar, Swiss
    - **Other toppings** (regular): lettuce, peppers, onions, tomatoes,
      jalapeños, cucumbers, pickles, guacamole, mushrooms
    - **Sauces** (regular): mayo, mustard, ketchup, ranch, thousand islands, vinaigrette
- Add **extra meat** and **extra cheese** (premium upcharge)
- Mark sandwich as **toasted / not toasted**

The builder screen shows:

- Current sandwich configuration
- Current sandwich price
- Current order total and projected total with this sandwich

### Drinks & Chips

- **Drinks**
    - Sizes: Small, Medium, Large
    - Flavors: Cola, Orange, Lemonade
    - User chooses size **and** flavor.

- **Chips**
    - Chip type: Classic, BBQ, Sour Cream, Salt & Vinegar, Jalapeño, etc.

### Checkout & Receipt

- Checkout screen shows:
    - All sandwiches with:
        - Size, bread, toasted
        - Meats, cheeses, other toppings, sauces (grouped)
        - Price per sandwich
    - All drinks (size + flavor + price)
    - All chips (type + price)
    - Subtotals for sandwiches, drinks, chips
    - **Grand total**

- When the user confirms:
    - The order is saved into a `receipts` folder.
    - Each receipt is its own file.
    - Filename format: `yyyyMMdd-HHmmss.txt`
        - Example: `20250329-121523.txt`

---

## 3. Pricing Rules (Per Capstone Spec)

All pricing logic is implemented in `PriceService`.

### Bread

| Size | Price |
|------|-------|
| 4"  | 5.50  |
| 8"  | 7.00  |
| 12" | 8.50  |

### Meats (Premium Topping per portion)

| Size | Price |
|------|-------|
| 4"  | 1.00  |
| 8"  | 2.00  |
| 12" | 3.00  |

### Extra Meat

| Size | Extra Price |
|------|-------------|
| 4"  | 0.50        |
| 8"  | 1.00        |
| 12" | 1.50        |

### Cheese (Premium Topping per portion)

| Size | Price |
|------|-------|
| 4"  | 0.75  |
| 8"  | 1.50  |
| 12" | 2.25  |

### Extra Cheese

| Size | Extra Price |
|------|-------------|
| 4"  | 0.30        |
| 8"  | 0.60        |
| 12" | 0.90        |

### Regular Toppings, Sauces, Sides

- All **regular toppings**, **sauces**, and **sides** are **included** in the bread price.
- They do **not** add extra cost.

### Drinks

| Size  | Price |
|-------|-------|
| Small | 2.00  |
| Medium| 2.50  |
| Large | 3.00  |

### Chips

- Any chip type: **1.50**

---

## 4. Project Structure

```text
VeryDeliSandwiches/
├─ receipts/                 # Receipt files (created at runtime)
├─ src/
│  └─ main/
│     └─ java/
│        └─ com/pluralsight/
│           ├─ models/
│           │  ├─ BreadType.java
│           │  ├─ SandwichSize.java
│           │  ├─ Sandwich.java
│           │  ├─ Order.java
│           │  ├─ Drink.java
│           │  └─ Chips.java
│           ├─ services/
│           │  └─ PriceService.java
│           └─ ui/
│              └─ UserInterface.java
├─ pom.xml
└─ README.md
```

---

## 5. Running the Application

### Prerequisites

- Java 17+ (or your course-required version)
- Maven

### Build & Run

```bash
# Clone the repo
git clone https://github.com/hckmsnbdy/VeryDeliSandwiches.git
cd VeryDeliSandwiches

# Build
mvn clean package

# Run
mvn exec:java -Dexec.mainClass="com.pluralsight.ui.UserInterface"
```

Alternatively, run `UserInterface.main()` directly from your IDE.

---

## 10. Credits
