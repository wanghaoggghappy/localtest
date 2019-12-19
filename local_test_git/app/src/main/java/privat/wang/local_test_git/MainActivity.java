package privat.wang.local_test_git;

import android.annotation.TargetApi;
import android.media.MediaCodec;
import android.media.MediaCodecInfo;
import android.media.MediaFormat;
import android.os.Build;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

import java.io.IOException;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        android.widget.Toast.makeText(this, "main haha", Toast.LENGTH_SHORT).show();
        setContentView(R.layout.activity_main);
        testMediaCodec();
    }

    @TargetApi(Build.VERSION_CODES.JELLY_BEAN)
    private void testMediaCodec() {
        TextView info = findViewById(R.id.info);

        StringBuilder builder = new StringBuilder();
        MediaCodec videoCodec = null;
        MediaCodec videoCodec1 = null;
        try {
            videoCodec =  MediaCodec.createDecoderByType("video/avc");
        } catch (IOException e)  {
            e.printStackTrace();
        }

        try {
            videoCodec1 =  MediaCodec.createEncoderByType("video/avc");
        } catch (IOException e)  {
            e.printStackTrace();
        }

        MediaFormat trackFormat = new  MediaFormat();
        trackFormat.setString(MediaFormat.KEY_MIME,  MediaFormat.MIMETYPE_VIDEO_AVC);
        trackFormat.setInteger(MediaFormat.KEY_FRAME_RATE,  15);
        trackFormat.setInteger(MediaFormat.KEY_COLOR_FORMAT,  MediaCodecInfo.CodecCapabilities.COLOR_FormatSurface);
        trackFormat.setInteger(MediaFormat.KEY_PROFILE,  8);
        trackFormat.setInteger(MediaFormat.KEY_HEIGHT,  1080);
        trackFormat.setInteger(MediaFormat.KEY_WIDTH,  1920);
        trackFormat.setInteger(MediaFormat.KEY_BIT_RATE,  1000000);
        trackFormat.setInteger(MediaFormat.KEY_I_FRAME_INTERVAL,  1);
        trackFormat.setInteger(MediaFormat.KEY_LEVEL,  256);
        trackFormat.setInteger(MediaFormat.KEY_BITRATE_MODE,  2);
        try {
            videoCodec.configure(trackFormat, null, null, MediaCodec.CONFIGURE_FLAG_ENCODE);
            videoCodec.start();
        } catch (Exception e) {
            e.printStackTrace();
            builder.append("解码器异常").append(e.getMessage()).append("\n");
        }

        try {
            videoCodec1.configure(trackFormat, null, null, MediaCodec.CONFIGURE_FLAG_ENCODE);
            videoCodec1.start();
        } catch (Exception e) {
            e.printStackTrace();
            builder.append("编码器异常").append(e.getMessage()).append("\n");
        }
        info.setText(builder.toString());
    }
}
