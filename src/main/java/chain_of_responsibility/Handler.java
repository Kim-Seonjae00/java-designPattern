package chain_of_responbility;

// 체인 공통 인터페이스
public interface Handler {
    // 다음 핸들러 연결 (체이닝 지원)
    Handler setNext(Handler next);

    // 입력 처리 후 다음으로 넘김 (필요 시 예외로 중단)
    String handle(String input);
}
