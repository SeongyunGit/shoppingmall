# 쇼핑몰 프로젝트

## 소개
이 프로젝트는 간단한 쇼핑몰 시스템을 구현한 프로젝트입니다.
사용자는 **회원가입**을 통해 서비스를 이용할 수 있으며, **관리자**와 **소비자**로 나뉘어 각각의 기능을 제공합니다.
소비자는 장바구니에 상품을 담아 구매할 수 있고, 관리자는 상품을 등록 및 관리할 수 있습니다.

## 주요 기능

### 1. 회원 관리
- **회원가입 및 로그인**
    - 사용자는 회원가입 후 서비스를 이용할 수 있습니다.
    - 로그인한 사용자만 쇼핑몰의 기능을 이용 가능합니다.

### 2. 사용자 권한
- **관리자**
    - 상품을 등록, 수정, 삭제할 수 있습니다.
    - 소비자의 구매내역을 확인할 수 있습니다.
- **소비자**
    - 상품을 검색하고 장바구니에 추가할 수 있습니다.
    - 장바구니에 담긴 상품을 구매할 수 있습니다.

### 3. 장바구니 기능
- 소비자는 원하는 상품을 장바구니에 추가할 수 있습니다.
- 장바구니에 담긴 상품 목록을 확인하고 구매를 진행할 수 있습니다.

### 4. 상품 관리
- 관리자는 상품을 등록하고, 등록된 상품의 정보를 수정하거나 삭제할 수 있습니다.
- 소비자는 관리자가 등록한 상품 목록을 확인할 수 있습니다.

## 기술 스택
- **프론트엔드**: HTML, CSS, JavaScript, Thymeleaf
- **백엔드**: Java, Spring Framework
- **데이터베이스**: MySQL

## 현재 고민 중인 사항
- 현재 이미지 파일을 `static/files` 디렉토리에 저장하여 관리하고 있습니다. 하지만 이 방법이 효율적이지 않을 수 있어, 이미지 파일을 효과적으로 관리할 수 있는 방법에 대해 고민 중입니다.
    - 예: 클라우드 스토리지 서비스(AWS S3, Google Cloud Storage 등)를 활용하거나, 데이터베이스와 연동하여 이미지를 관리하는 방안.
