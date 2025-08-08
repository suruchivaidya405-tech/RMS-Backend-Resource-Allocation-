# Resource Lifecycle Management System

## 🌐 API Endpoints Summary
**Total Endpoints:** 14  
**Resource Service (8080):** 8 endpoints  
**Report Service (8084):** 6 endpoints

---

## 🔧 Resource Service (`http://localhost:8080`)

### 🔹 Allocations
| Method | Endpoint                | Description                          | Request Body Example                  |
|--------|-------------------------|--------------------------------------|---------------------------------------|
| POST   | `/api/allocations`      | Create new allocation                | ```json {"projectId":"PROJ-101","resourceId":"EMP-001","status":"ACTIVE"}``` |
| GET    | `/api/allocations`      | List all allocations                | -                                     |
| GET    | `/api/allocations/{id}` | Get specific allocation             | -                                     |
| PUT    | `/api/allocations/{id}` | Update allocation                   | Same as POST                          |

### 🔹 Requests
| Method | Endpoint                     | Description                          | Request Body Example                  |
|--------|------------------------------|--------------------------------------|---------------------------------------|
| POST   | `/api/requests/release`      | Create release request              | ```json {"allocationId":1,"reason":"Project completed"}``` |
| POST   | `/api/requests/additional`   | Request additional resources        | ```json {"projectId":"PROJ-101","skillRequired":"Java","count":2}``` |
| POST   | `/api/requests/extension`    | Request extension                   | ```json {"allocationId":1,"newEndDate":"2023-12-31"}``` |

### 🔹 Approvals
| Method | Endpoint          | Description              | Request Body Example                  |
|--------|-------------------|--------------------------|---------------------------------------|
| POST   | `/api/approvals`  | Approve/reject requests  | ```json {"requestId":"1","requestType":"RELEASE","status":"APPROVED"}``` |

---

## 📈 Report Service (`http://localhost:8084`)

### 🔍 Reports
| Method | Endpoint                          | Description                  | Parameters               |
|--------|-----------------------------------|------------------------------|--------------------------|
| GET    | `/api/reports/allocations-summary`| Allocation statistics        | -                        |
| GET    | `/api/reports/project-status`     | Project-wise summary         | `?projectId=PROJ-101`    |
| GET    | `/api/reports/request-queue`      | Pending requests             | `?type=RELEASE`          |
| GET    | `/api/reports/resource-utilization`| Resource usage analytics    | `?timeframe=30days`      |
| GET    | `/api/reports/historical-trends`  | Historical data trends       | `?metric=allocations`    |

### ⚙️ Utility
| Method | Endpoint          | Description              |
|--------|-------------------|--------------------------|
| GET    | `/api/health`     | Service health check     |

---

## 🔐 Authentication
```http
POST /auth/login
Content-Type: application/json

{
  "username": "admin",
  "password": "admin123"
}

Response:
{
  "token": "eyJhbGciOiJIUzI1NiIs..."
}