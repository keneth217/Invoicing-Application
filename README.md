# 🧾 Invoice Management System - Backend

A robust Spring Boot backend application designed to handle invoicing, payments, and ledger management for small to medium businesses. Built with scalability and multitenancy in mind.

## 🚀 Features

### ✅ Core Modules

- **Customer Management**
  - Create, update, soft-delete customers
  - Validate existence before invoice creation

- **Invoice Management**
  - Auto-generate invoice numbers (e.g., `INV-0001`)
  - Create invoices with items
  - Track invoice status: `UNPAID`, `PARTIALLY_PAID`, `PAID`
  - Validate total amount and invoice item consistency

- **Quotation Management** (optional)
  - Convert quotations to invoices

- **Payment Vouchers**
  - Associate payments to invoices
  - Auto-generate voucher numbers (e.g., `VCH-0001`)
  - Prevent overpayment logic
  - Support multiple payment methods (e.g., MPESA, BANK, CASH)
  - Automatically update related invoice status

- **Receipts**
  - Automatically generated after each payment
  - Linked to both invoice and payment voucher
  - Auto-generated receipt numbers (e.g., `RCT-0001`)

- **Ledger**
  - Centralized record of all financial transactions
  - Record both debit and credit entries
  - Compute running balances
  - View full ledger with summary (total credit, debit, balance)
  - Linked to invoices, vouchers, and receipts

### ⚙️ Technical Features

- Built with **Spring Boot 3**
- Uses **MySQL** as the main database
- Clean architecture with layered separation:
  - Controller → Service → Repository → Entity
- DTO mapping using dedicated mappers
- Global error handling
- Clean exception messages with consistent `ErrorResponse` format
- UUID-ready for multi-branch expansion
- Transactional operations where needed
- Soft-deletion support

### 📦 API Endpoints (Summary)

#### Customer

| Method | Endpoint              | Description               |
|--------|------------------------|---------------------------|
| POST   | `/api/v1/customers`    | Add a new customer        |
| GET    | `/api/v1/customers`    | List all customers        |
| GET    | `/api/v1/customers/{id}` | Get a single customer     |
| PUT    | `/api/v1/customers/{id}` | Update customer           |
| DELETE | `/api/v1/customers/{id}` | Soft delete customer      |

#### Invoice

| Method | Endpoint              | Description               |
|--------|------------------------|---------------------------|
| POST   | `/api/v1/invoices`     | Create invoice            |
| GET    | `/api/v1/invoices`     | List all invoices         |
| GET    | `/api/v1/invoices/{id}`| View invoice details      |

#### Payment Voucher

| Method | Endpoint                            | Description               |
|--------|--------------------------------------|---------------------------|
| POST   | `/api/v1/payments/{invoiceId}`       | Pay invoice & generate receipt |
| GET    | `/api/v1/payments`                   | Get all payment vouchers  |

#### Receipt

| Method | Endpoint                              | Description                   |
|--------|----------------------------------------|-------------------------------|
| GET    | `/api/v1/receipts`                     | List all receipts             |
| GET    | `/api/v1/receipts/voucher/{voucherId}` | Get receipts by voucher       |

#### Ledger

| Method | Endpoint                  | Description               |
|--------|----------------------------|---------------------------|
| GET    | `/api/v1/ledger/report`    | Get ledger entries + summary |

---

## 🧰 Tech Stack

- **Java 17+**
- **Spring Boot 3**
- **Lombok**
- **Spring Data JPA**
- **MySQL**
- **MapStruct / Manual Mapping**
- **Hibernate 6**
- **REST APIs**

---

## 🏁 Setup Instructions

### 🐳 Local Setup

1. Clone the repository

```bash
git clone https://github.com/yourusername/invoice-backend.git
cd invoice-backend
