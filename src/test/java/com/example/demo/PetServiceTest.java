package com.example.demo;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.times;

// Spring全体を立ち上げない「超軽量」な設定
@ExtendWith(MockitoExtension.class)
public class PetServiceTest {
    
    // DBにアクセスする Repository を「モック(影武者)」にする
    @Mock
    private PetRepository petRepository;

    // その影武者を注入(Inject)した、本物の Service を用意する
    @InjectMocks
    private PetService petService;

    // 1つ目のテスト
    @Test
    public void ペットの登録が成功し正しいメッセージが返るかをテストする() {
        // 準備：影武者に「save() と言われたら、とりあえずOKを返してね」という台本を渡す
        // (どんなPetクラスが来ても、たま/スコティッシュフォールド を返すフリをします)
        when(petRepository.save(any(Pet.class))).thenReturn(new Pet("たま", "スコティッシュフォールド"));

        // 実行：影武者がセットされた Service を呼び出す！本物のDBには一切アクセスしない
        String result = petService.registerPet("たま", "スコティッシュフォールド");
    
        // 検証1：期待通りのメッセージが返ってきたか？
        assertEquals("たま(スコティッシュフォールド)を登録しました！", result);

        // 検証2：影武者の save メソッドが、ちゃんと「1回だけ」呼ばれたか？(呼び忘れがないかのチェック)
        verify(petRepository, times(1)).save(any(Pet.class));
    }

    // 2つ目のテスト
    @Test
    public void すでに同じ名前がいる場合はエラーになることをテストする() {

        // 準備：影武者に「existsById("たま") と聞かれたら、もういるよ(true)と答えてね」と指示する
        when(petRepository.existsById("たま")).thenReturn(true);

        // 実行&検証：登録しようとするとエラー(IllegalArgumentException)が飛んでくるはず！と待ち構える
        IllegalArgumentException exception = assertThrows(IllegalArgumentException.class, () -> {
            petService.registerPet("たま", "スコティッシュフォールド");
        });

        // 検証1：狙い通りのエラーメッセージが返ってきたか？
        assertEquals("すでに登録されている名前です: たま", exception.getMessage());

        // 検証2：エラーで弾かれたので save(保存) は「絶対に1回も呼ばれていない」こと
        verify(petRepository, times(0)).save(any(Pet.class));
    }
}
