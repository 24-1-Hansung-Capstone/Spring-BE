package com.example.HansungCapstone.DTO.Es;

import lombok.RequiredArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import java.lang.reflect.Field;

@Setter
@ToString
public class EsDtoWrapper{
    public EsDto esDto;
    public String preview;
    public String category;

//    // mainBody에서 앞부분 30글자를 추출하여 preview 필드에 설정하는 메서드
//    public void generatePreview() {
//        if (esDto != null) {
//            Class<?> clazz = esDto.getClass();
//            Field mainBodyField = null;
//
//            // EsDto의 하위 클래스에서 mainBody 필드를 찾습니다.
//            while (clazz != null && mainBodyField == null) {
//                try {
//                    mainBodyField = clazz.getDeclaredField("mainBody");
//                } catch (NoSuchFieldException e) {
//                    clazz = clazz.getSuperclass(); // 상위 클래스로 이동하여 다시 검색합니다.
//                }
//            }
//
//            if (mainBodyField != null) {
//                try {
//                    mainBodyField.setAccessible(true); // private 필드에 접근하기 위해 설정합니다.
//                    String mainBodyValue = (String) mainBodyField.get(esDto);
//                    preview = mainBodyValue != null ? mainBodyValue.substring(0, Math.min(mainBodyValue.length(), 30)) : "";
//                } catch (IllegalAccessException e) {
//                    // 예외 처리
//                }
//            }
//        }
//    }
}