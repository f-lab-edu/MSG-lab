# JVM warm-up

성능 테스트 전 TPS 그래프 모양을 천천히 증가하는 선형 그래프로 예상했습니다.

실험 결과 예상과 다른 계단 모양의 그래프가 출력되었습니다.

특히 테스트 초기에 TPS 지표가 낮은 문제가 발견되었습니다.

<img src="https://user-images.githubusercontent.com/46879264/215943059-0b118ad9-9a77-4d60-970e-8754afd8f325.png" width="500" height="300">

_계단 모양 그래프, 초기 처리율은 50을 못넘음_
<br><br>
이슈를 멘토님과 공유했고, wram-up 문제라는 것을 알 수 있었습니다.

<br><br>
해결법으로 if kakao 2022 세션에 소개된 "컴파일 로그로 웜업 지표 만들기"를 사용했습니다.

어플리케이션을 구동시키는 최초 시점부터 문제가 해소되는 시점의 컴파일 지표를 확인했습니다.

컴파일이 충분히 진행되는 시점까지 10개의 쓰레드로 약 5분이면 도달할 수 있어 웜업 시간을 5분으로 설정하여 해결했습니다.

### threshold 지표

<img src="https://user-images.githubusercontent.com/46879264/215947948-cc8c51ea-bf49-4701-9d8b-99e1d09380e8.png" width="700" height="100">

### warm-up 이전 Tier 4 컴파일 수치

<img src="https://user-images.githubusercontent.com/46879264/215949678-343af710-9c57-455c-828a-f9b9bd92450a.jpeg" width="800" height="100">

### warm-up 이후 Tier 4 컴파일 수치

<img src="https://user-images.githubusercontent.com/46879264/215949683-1c0ea515-8638-410e-b4be-a789cf128fa3.jpeg" width="800" height="100">

<br>
<img src="https://user-images.githubusercontent.com/46879264/215943130-4696d7c4-71aa-49f6-8ffa-756aadd2fc65.png" width="500" height="300">

_말끔히 해결된 초기 지연 문제_
<br><br>
인스턴스의 리소스 사용량이 웜업 초기에는 살짝 높지만, 우려할 수준은 아니었습니다.

특별히 리소스에 대한 리밋을 설정한다면, 이 점은 주의해야한다는 사실도 알게 되었습니다.

웜업 과정을 수동으로 진행하고 있었습니다.

커스텀 헬스 인디케이터 등을 활용해 자동화 해보면 좋겠다는 아쉬움도 있었습니다.