package refresh.ManageSystem.dao;

import lombok.Getter;
import lombok.Setter;
import refresh.ManageSystem.dto.PageDTO;

/**
 * mowgood
 *
 * 페이징 처리
 * pageDTO : 현재 페이지 정보를 담은 dto
 * totalCount : DB에 담긴 전체 데이터 수
 * totalPage : 전체 페이지 수
 * startPage : 시작 페이지
 * endPage : 끝 페이지
 * prev : 이전 페이지 존재 여부
 * next : 다음 페이지 존재 여부
 * totalPageCount : 표시할 페이지 버튼 수
 */
@Getter @Setter
public class PageDAO {

    private PageDTO pageDTO;
    private int totalCount;
    private int totalPage;
    private int startPage;
    private int endPage;
    private boolean prev;
    private boolean next;
    private int totalPageCount = 5;

    public void pageMaker() {
        totalPage = (totalCount % pageDTO.getPerPageNum() == 0) ? totalCount / getPageDTO().getPerPageNum() : totalCount / pageDTO.getPerPageNum() + 1;
        startPage = ((pageDTO.getPage()-1) / totalPageCount) * totalPageCount + 1;
        endPage = startPage + (totalPageCount - 1);
        if(endPage > totalPage) {
            endPage = totalPage;
        }
        prev = startPage == 1 ? false : true;
        next = endPage * pageDTO.getPerPageNum() < totalCount ? true : false;
    }
}
