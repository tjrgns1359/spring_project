# 카네스블랙 카페

저바 기반 풀스택 개발자 인턴과정의 개인 프로젝트입니다.

- **프로젝트 기간:** 2025.10.01 ~ 2025.11.28  
- **개발자:** 최석훈 (개인프로젝트)

---

## 목차

1. [프로젝트 개요](#프로젝트-개요)
2. [프로젝트 구성](#프로젝트-구성)
3. [프로젝트 수행 및 결과](#프로젝트-수행-및-결과)
4. [프로젝트 후기](#프로젝트-후기)

---

## 1. 프로젝트 개요

### 프로젝트 필요성

- 기본적인 게시판 CRUD 구현
- 스프링 시큐리티를 통한 인증 및 인가 과정 구현
- XSS 공격 방지, CSRF 방지 등 기본적인 보안 처리 해결방법 제시

### 서비스 대상

- 고객

### 담당 업무

- Spring Security 구현
- API 설계
- DB 설계
- 프로젝트 환경 구성
- 게시판(CRUD) 개발
- 로그인/로그아웃 및 회원가입 기능 구현

---

## 2. 프로젝트 구성

### 기술 스택

| 구분           | 기술                                     |
|----------------|------------------------------------------|
| OS             | Windows                                  |
| Server         | Amazon AWS                               |
| Database       | MySQL                                    |
| Testing        | Swagger                                  |
| Frontend       | HTML5, CSS3, JavaScript                  |
| Backend        | Spring Boot (v3.5.6), Java (OpenJDK-21)  |
| IDE            | Visual Studio Code, Eclipse               |
| Collaboration  | GitHub, Notion                           |

---

### 시스템 아키텍쳐

#### 시퀀스 다이어그램
![시퀀스 다이어그램](sequence-diagram.drawio-1.png)

#### 데이터베이스 구조
<img width="485" height="351" alt="image" src="https://github.com/user-attachments/assets/7a77e2b0-5d9a-4ff7-bbb2-6b2c21161814" />


#### API 설계
<img width="2400" height="1600" alt="api_methods_table" src="https://github.com/user-attachments/assets/0cbf6a34-4a5c-4df0-a24d-1ddeae1dd481" />


---

## API 명세서

| Method | Endpoint             | 설명           |
|--------|----------------------|----------------|
| DELETE | /menu/delete/{idx}   | 메뉴 삭제      |
| GET    | /menu/{idx}          | 메뉴 상세 조회 |
| GET    | /menu/all            | 전체 메뉴 조회 |
| POST   | /menu/add            | 메뉴 추가      |
| PUT    | /menu/update/{idx}   | 메뉴 수정      |
| PUT    | /menu/count/{idx}    | 조회수 증가    |


## 3. 프로젝트 수행과정 및 결과

-인턴과정 학습 / 개인 프로젝트임 
-개발기간 약 3주 
-개선해야할점 

## 4. 프로젝트 후기
