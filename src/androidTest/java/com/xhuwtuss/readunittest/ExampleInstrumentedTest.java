package com.xhuwtuss.readunittest;

import android.content.Context;
import android.support.test.InstrumentationRegistry;
import android.support.test.runner.AndroidJUnit4;
import android.util.Log;

import com.dy45.reader.Biz.TaogulaBiz;
import com.dy45.reader.entity.ArticleDTO;
import com.dy45.reader.http.VolleyUtil;

import org.junit.Test;
import org.junit.runner.RunWith;

import static org.junit.Assert.*;

/**
 * Instrumentation test, which will execute on an Android device.
 *
 * @see <a href="http://d.android.com/tools/testing">Testing documentation</a>
 */
@RunWith(AndroidJUnit4.class)
public class ExampleInstrumentedTest {
    @Test
    public void useAppContext() throws Exception {
        // Context of the app under test.
        Context appContext = InstrumentationRegistry.getTargetContext();
        VolleyUtil.initVolley(appContext);

        TaogulaBiz.getArticleList(articleDTOs -> {
            if(articleDTOs.size()>0){
                for (ArticleDTO article :articleDTOs) {
                    Log.i("UnitTest",article.getUrl());
                }
            }
        });

    }
}
