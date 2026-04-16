## 🚀 3단계 - 영화 예매(데이터베이스)

### 구현 전략

- 도메인 객체 변경을 최소화한다. `Screening` 생성자에 `reservedSeatNumbers` 기본값 파라미터 하나만 추가하여 DB 복원을 지원한다.
- ID는 도메인 객체에 두지 않고 Repository 레이어에서 관리한다.
- Repository 인터페이스는 `repository` 패키지에, JDBC 구현체는 `db` 패키지에 분리한다.
- 로컬 실행은 파일 기반 H2, 테스트는 In-Memory H2를 사용한다.

### 패키지 구조

```
model/          — 핵심 비즈니스 로직 (기존 유지)
view/           — 콘솔 입출력 (기존 유지)
controller/     — 흐름 제어 (기존 유지)
repository/     — Repository 인터페이스
db/             — JDBC 구현체, ConnectionManager, 스키마
```

### 기능 목록

#### 1. H2 의존성 추가 및 DB 스키마 설계

- `build.gradle.kts`에 H2 의존성 추가
- `schema.sql` 작성 (movies, screenings, reservations, reservation_seats 테이블)
- `data.sql` 작성 (초기 영화/상영 데이터)
- `ConnectionManager` 구현 (로컬/테스트 환경 분리)

#### 2. Movie/Screening 조회 Repository 구현

- `MovieRepository` 인터페이스 정의
- `ScreeningRepository` 인터페이스 정의
- `JdbcMovieRepository` 구현 (movies 테이블 조회)
- `JdbcScreeningRepository` 구현 (screenings 조회 + reservedSeatNumbers 복원)

#### 3. Reservation 저장 Repository 구현

- `Screening` 생성자에 `reservedSeatNumbers: Set<SeatNumber> = emptySet()` 추가
- `ReservationRepository` 인터페이스 정의
- `JdbcReservationRepository` 구현 (reservation + reservation_seats 저장)

#### 4. 콘솔 앱 DB 연동

- `Scheduler`를 DB 기반으로 교체 (`DbScheduler` 구현)
- `Application.kt` 수정 (DB 기반 의존성 주입)

#### 5. DB 연동 테스트 작성

- `JdbcMovieRepositoryTest` (영화 목록 조회)
- `JdbcScreeningRepositoryTest` (상영 조회, 예약 좌석 복원)
- `JdbcReservationRepositoryTest` (예약 저장, 중복 좌석 검증)
