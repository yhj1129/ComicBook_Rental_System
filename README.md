- 패키지 구조
   - ````app```` : Main Application (Menu 처리 및 View 처리용)
   - ````comm```` : 공통코드와 Utility
   - ````dto```` : 데이터 저장 클래스 
   - ````factory```` : 객체 생생 팩토리 클래스
   - ````controller```` : 클라이언트 요청/응답 처리 컨트롤 및 비즈니스 처리
   - ````repository```` :  데이터 처리 관련 클래스 (조회/저장/수정/삭제)
 - 각 Role별로 패키지를 나눠서 관련 클래스끼리 함께 패키지화
   - interace 구현 클래스는 인터페이스명+'Impl'을 붙이도록 Naming
   - 예시
     - ```com.cmbookrental.prj.dto``` 패키지
       - ```ComicBookDTO```, ```RentalDTO```
     - ```com.cmbookrental.prj.controller``` 패키지
       - interace ```ComicBookController```, ```RentalController```
       - interace 구현 클래스 ```ComicBookControllerImpl```, ```RentalControllerImpl```
     - ```com.cmbookrental.prj.repository``` 패키지
       - ```ComicBookRepository```, ```RentalRepository```
     - ```com.cmbookrental.prj.app``` 패키지
       - ```ComicBookRentalApp```
   
          
      

