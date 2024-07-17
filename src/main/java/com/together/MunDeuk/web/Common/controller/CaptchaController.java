package com.together.MunDeuk.web.Common.controller;

import com.google.cloud.texttospeech.v1.AudioConfig;
import com.google.cloud.texttospeech.v1.AudioEncoding;
import com.google.cloud.texttospeech.v1.SsmlVoiceGender;
import com.google.cloud.texttospeech.v1.SynthesisInput;
import com.google.cloud.texttospeech.v1.TextToSpeechClient;
import com.google.cloud.texttospeech.v1.VoiceSelectionParams;
import com.google.code.kaptcha.Constants;
import com.google.code.kaptcha.impl.DefaultKaptcha;
import com.google.protobuf.ByteString;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.awt.image.BufferedImage;
import java.io.IOException;
import javax.imageio.ImageIO;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RestController
@RequestMapping("/captcha")
public class CaptchaController {
  @Autowired
  private DefaultKaptcha defaultKaptcha;

  @GetMapping("/captchaImg")
  public void generateCaptcha(HttpServletRequest request,HttpServletResponse response) throws IOException {
    String captchaText = defaultKaptcha.createText();
    request.getSession().setAttribute(Constants.KAPTCHA_SESSION_KEY, captchaText);
    BufferedImage captchaImage = defaultKaptcha.createImage(captchaText);

    response.setContentType("image/png");
    ImageIO.write(captchaImage, "png", response.getOutputStream());
  }

  @GetMapping("/captchaAudio")
  public void getCaptchaAudio(HttpServletRequest request, HttpServletResponse response) throws IOException {
    // 캡차 텍스트를 기반으로 오디오 파일 생성 (구현 필요)
    // 예시: 오디오 파일을 서버에서 읽어와 스트림으로 반환

    String captchaText = (String) request.getSession().getAttribute(Constants.KAPTCHA_SESSION_KEY);
    // 오디오 파일 생성 및 반환 로직 구현
    log.debug("+++++++++++++");
    log.debug(captchaText);

    if (captchaText == null) {
      response.sendError(HttpServletResponse.SC_BAD_REQUEST, "Captcha not found");
      return;
    }

    try (TextToSpeechClient textToSpeechClient = TextToSpeechClient.create()) {
      SynthesisInput input = SynthesisInput.newBuilder().setText(captchaText).build();
      VoiceSelectionParams voice = VoiceSelectionParams.newBuilder()
          .setLanguageCode("ko-KR") // 언어 코드 설정
//          .setSsmlGender(VoiceSelectionParams.SsmlVoiceGender.NEUTRAL) // 성별 설정
          .setSsmlGender(SsmlVoiceGender.NEUTRAL) // SSML_VOICE_GENDER_UNSPECIFIED, MALE, FEMALE, NEUTRAL, UNRECOGNIZED
          .build();
      AudioConfig audioConfig = AudioConfig.newBuilder()
          .setAudioEncoding(AudioEncoding.MP3) // 오디오 포맷 설정
          .build();

      ByteString audioContents = textToSpeechClient.synthesizeSpeech(input, voice, audioConfig).getAudioContent();
      response.setContentType("audio/mpeg");
      response.setContentLength(audioContents.size());
      response.getOutputStream().write(audioContents.toByteArray());
    }
  }
}
