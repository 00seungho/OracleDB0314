package kopo.ac.kr.oracledb0314.repository;

import kopo.ac.kr.oracledb0314.entity.Memo;
import kopo.ac.kr.oracledb0314.repository.MemoRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;
import java.util.stream.IntStream;

 @SpringBootTest
public class  MemoRepositorysTests {

    @Autowired
    MemoRepository memoRepository;

    @Test
    public void testClass(){
        System.out.println((memoRepository.getClass().getName()));
    }

    @Test
    public void testInsertDummies(){
        IntStream.rangeClosed(1,100).forEach(i ->{
            Memo memo = Memo.builder().memoText("Dummy Data Test" + i).build();
            memoRepository.save(memo);
            //JPA 레파지토리의 save를 이용해 insert를 한다
            // memo라는 엔티티
        });
    }

     @Test
     public void testSelcect(){
        Long mno = 100L;
        Optional<Memo> result = memoRepository.findById(mno);
         //MemoRepository의 findById(Memo Entity 객체의 Id로설정된 필드값)를 호출해서 select 한다.
         //findById()호출되면 바로 select문을 실행한다.
         //nullpointexception이 발생되지 않도록 Null값을 체크 할 수 있다. 결과 Optional의 isPresent() 메서드 사용
        //Optional로 반환함
         System.out.println("====================================================");
         if (result.isPresent()){
             Memo memo = result.get();
             System.out.println(memo);
         }
     }
     @Test
     @Transactional
     public void testSelcect2(){
         Long mno = 100L;
         //MemoRepository의 getOne(Memo Entity 객체의 Id로 설정된 필드값)을 호출해서 select 한다.
         //getOne는 바로 실행되지 않고, Memo Entity가 필요할 때 select를 실행한다
         Memo memo = memoRepository.getOne(mno);
         //Optional로 반환함
         System.out.println("====================================================");
             System.out.println(memo);
         }
     @Test
     public void testUpdate(){
        //MemoRepository의 save(memo Entity객체의 참조값)을 호출해서 update 한다.
         //save() 는 호출되면 먼저 select를 하기 때문에 기존에 Entity가 있을 때는 update를 실행한다.

         Memo memo = Memo.builder().mno(95L).memoText("수정된 텍스트").build();
         Memo memo1 = memoRepository.save(memo);

         System.out.println(memo1);
     }
     @Test
     public void testDelete(){
        //MemoRepository의 deleteById(Memo Entity 객체의 Id로 설정된 필드값)을 호출해서 delete 한다.
         Long mno = 100L;
         memoRepository.deleteById(mno);
    //주석
     }  

}


