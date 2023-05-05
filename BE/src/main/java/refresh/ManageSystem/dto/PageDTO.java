package refresh.ManageSystem.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

/**
 * mowgood
 *
 * 현재 페이지 정보를 담은 DTO
 * start : 시작 인덱스
 * page : 현재 페이지 번호
 * perPageNum : 한 페이지에 보여질 데이터 수
 */
@Getter @Setter @AllArgsConstructor
public class PageDTO {
    private int start;
    private int page;
    private int perPageNum;

    // 객체 생성시 기본 초기값 지정
    public PageDTO() {
        this.start = 0;
        this.page = 1;
        this.perPageNum = 5;
    }
}
