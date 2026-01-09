
# 📄 PDF Text Extractor – Spring Boot OCR API

A Spring Boot REST API that extracts text from PDF files.
It supports **both selectable (text-based) PDFs and scanned/image-based PDFs** using an automatic **OCR fallback mechanism**.

---

## 🚀 Project Overview

Many PDF files (profile reports, scanned documents, reports with charts) do not allow text selection or copying.
This project solves that problem by:

1. Trying to extract text directly from the PDF
2. Automatically falling back to OCR if the PDF is image-based
3. Returning extracted text via a REST API

---

## 🧠 How It Works (High-Level Flow)

```
Client uploads PDF
        ↓
Try PDFBox text extraction
        ↓
Is text empty?
   ├── NO → Return extracted text
   └── YES → Convert PDF → Images → OCR (Tesseract)
                     ↓
                 Return OCR text
```

---

## 🛠️ Tech Stack

| Layer            | Technology        |
| ---------------- | ----------------- |
| Language         | Java 17           |
| Framework        | Spring Boot 3     |
| PDF Parsing      | Apache PDFBox     |
| OCR Engine       | Tesseract OCR     |
| Java OCR Wrapper | Tess4J            |
| API Docs         | Swagger / OpenAPI |
| Build Tool       | Maven             |

---

## 📂 Project Structure

```
pdf-text-extractor/
├── controller        → REST API layer
├── service           → Business logic
├── processor         → PDF analysis & conversion
├── exception         → Custom exceptions
├── config            → Async / configuration
├── model             → API responses
└── resources         → application.yml
```

---

## 📌 API Endpoint

### Extract Text from PDF

```
POST /api/v1/pdf/extract
```

### Request

* `multipart/form-data`
* Parameter: `file` (PDF)

### Response (JSON)

```json
{
  "fileName": "profile-report.pdf",
  "extractionType": "OCR",
  "content": "Extracted text here..."
}
```

---

## 🔧 Prerequisites (IMPORTANT)

### 1️⃣ Java

* Java **17**
* Verify:

```bash
java -version
```

---

### 2️⃣ Maven

* Maven 3.8+
* Verify:

```bash
mvn -v
```

---

### 3️⃣ Tesseract OCR (MANDATORY for OCR)

⚠️ **Tess4J does NOT include Tesseract OCR**
You must install Tesseract separately.

---

## 🪟 Windows Setup (Step-by-Step)

### Step 1: Install Tesseract OCR

Download installer from:
👉 [https://github.com/UB-Mannheim/tesseract/wiki](https://github.com/UB-Mannheim/tesseract/wiki)

Install to:

```
C:\Program Files\Tesseract-OCR
```

✔ Select **English language**
✔ Add Tesseract to **PATH**

---

### Step 2: Verify Installation

Open **Command Prompt**:

```bat
tesseract --version
```

```bat
tesseract --list-langs
```

Expected output:

```
eng
```

If this does not work → OCR will not work in Java.

---

## ⚙️ Application Configuration

### `application.yml`

```yaml
spring:
  servlet:
    multipart:
      max-file-size: 20MB
      max-request-size: 20MB

tesseract:
  datapath: C:/Program Files/Tesseract-OCR/tessdata
  language: eng
```

---

## 🧪 Running the Application

### Build

```bash
mvn clean install
```

### Run

```bash
mvn spring-boot:run
```

Application starts at:

```
http://localhost:8080
```

---

## 🔍 Swagger UI

Once the app is running, open:

```
http://localhost:8080/swagger-ui.html
```

Use Swagger to upload a PDF and test the API.

---

## 🧪 Testing via curl

```bash
curl -X POST http://localhost:8080/api/v1/pdf/extract \
  -F "file=@/full/path/to/sample.pdf"
```

---

## 🧠 Important Design Decisions

### Why OCR is synchronous

* The API is request–response based
* OCR result is required immediately
* Async is better suited for background jobs with polling

---

### Why custom exception handling

* Prevents native crashes from leaking to API
* Centralized error handling
* Clean API responses

---

## ⚠️ Common Errors & Fixes

### ❌ Error: `Invalid return type for async method`

**Cause:** `@Async` used with `String` return type
**Fix:** Remove `@Async` or return `CompletableFuture<T>`

---

### ❌ Error: `eng.traineddata not found`

**Cause:** Tesseract not installed or wrong tessdata path
**Fix:** Install Tesseract and configure `tesseract.datapath`

---

### ❌ Error: `Invalid memory access`

**Cause:** Native Tesseract crash due to missing language files
**Fix:** Verify `tesseract --list-langs`

---

## 🧩 Limitations

* Charts are not converted to numerical data
* Tables are returned as raw text
* OCR accuracy depends on PDF quality

---

## 🚀 Future Enhancements

* Async job-based OCR with status polling
* Table extraction (AWS Textract / Azure Form Recognizer)
* Dockerized deployment
* Text indexing (Elasticsearch)
* Multi-language OCR support

---

## 🎯 Interview Talking Points

* Automatic OCR fallback strategy
* Native dependency handling in Java
* Clean layered architecture
* Real-world PDF processing challenges
* Environment-based configuration

---

## 🤝 Contribution

Feel free to fork, improve, and raise PRs.

---
### Note:
Added testing word and pdf file under resources/templates section, please feel free to use and upload from postman client for testing and validation.