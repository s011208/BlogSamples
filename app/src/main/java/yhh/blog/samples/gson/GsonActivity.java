package yhh.blog.samples.gson;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;

import com.google.gson.FieldNamingPolicy;
import com.google.gson.FieldNamingStrategy;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonDeserializationContext;
import com.google.gson.JsonDeserializer;
import com.google.gson.JsonElement;
import com.google.gson.JsonParseException;
import com.google.gson.TypeAdapter;
import com.google.gson.reflect.TypeToken;
import com.google.gson.stream.JsonReader;
import com.google.gson.stream.JsonWriter;

import java.io.IOException;
import java.lang.reflect.Field;
import java.lang.reflect.Modifier;
import java.lang.reflect.Type;
import java.util.List;


public class GsonActivity extends AppCompatActivity {
    private static final String HACKER_NEWS_STORY_JSON = "{\"by\":\"loeg\",\"descendants\":190,\"id\":15276926,\"kids\":[15277114,15277280,15277249,15277716,15277680,15277068,15277067,15277505,15277360,15277361,15277390,15277469,15278963,15277160,15277806,15277070,15277210,15277824,15278733,15277177,15277240,15278663,15277228,15277704,15277134,15277063,15277363,15277323,15277421,15278753],\"score\":410,\"time\":1505749772,\"title\":\"Equifax Stock Sales Are the Focus of U.S. Criminal Probe\",\"type\":\"story\",\"url\":\"https://www.bloomberg.com/news/articles/2017-09-18/equifax-stock-sales-said-to-be-focus-of-u-s-criminal-probe\"}";
    private static final String HACKER_NEWS_STORY_JSON_ALIAS_ID = "{\"by_id\":\"loeg\",\"descendants\":190,\"id\":15276926,\"kids\":[15277114,15277280,15277249,15277716,15277680,15277068,15277067,15277505,15277360,15277361,15277390,15277469,15278963,15277160,15277806,15277070,15277210,15277824,15278733,15277177,15277240,15278663,15277228,15277704,15277134,15277063,15277363,15277323,15277421,15278753],\"score\":410,\"time\":1505749772,\"title\":\"Equifax Stock Sales Are the Focus of U.S. Criminal Probe\",\"type\":\"story\",\"url\":\"https://www.bloomberg.com/news/articles/2017-09-18/equifax-stock-sales-said-to-be-focus-of-u-s-criminal-probe\"}";
    private static final String HACKER_NEWS_TOP_STORIES_JSON = "[15291961,15291915,15292029,15291809,15292401,15293741,15293706,15293884,15293573,15289611,15293855,15291283,15292551,15292357,15294171,15292296,15290200,15290718,15289766,15290117,15290525,15288486,15290687,15290607,15291636,15289917,15291447,15289676,15289689,15289654,15289564,15291552,15288720,15291716,15287376,15284976,15281364,15285402,15288120,15289636,15278883,15289431,15287723,15289221,15286956,15291377,15289397,15287989,15291493,15288920,15282766,15281914,15289438,15292643,15284417,15289437,15285927,15276833,15282132,15281455,15287100,15288499,15281522,15291800,15280961,15286463,15285264,15282469,15286089,15275972,15285380,15286783,15274110,15285970,15278160,15289747,15280064,15268726,15289446,15285514,15285112,15283928,15272152,15277604,15290547,15286969,15289228,15285832,15286204,15288333,15290247,15288555,15290190,15284020,15289474,15291477,15281137,15277351,15289044,15274423,15285639,15270189,15281157,15285319,15278063,15281061,15274509,15282535,15288703,15291350,15288273,15289588,15268720,15275277,15274339,15276357,15265507,15287929,15277384,15279951,15272374,15275443,15275182,15284252,15281532,15277244,15290732,15278480,15290364,15270133,15288168,15274058,15282148,15274848,15274756,15278344,15271863,15279424,15275145,15278151,15283947,15276926,15272394,15273228,15276783,15271430,15274553,15278530,15275271,15290344,15280736,15278147,15280671,15281506,15270799,15282234,15288733,15276411,15278752,15273846,15275026,15276976,15272413,15272759,15279359,15281095,15285684,15277834,15278126,15280753,15288654,15267022,15274943,15271160,15290130,15289789,15291127,15280227,15289613,15269360,15274186,15265753,15269628,15283441,15274644,15270087,15281390,15279307,15287634,15271124,15281977,15278488,15290943,15273262,15266331,15290935,15279840,15291795,15283420,15288638,15268944,15274211,15275281,15277622,15283900,15272721,15282509,15286454,15274123,15268843,15288200,15272245,15269827,15270263,15267111,15264191,15271611,15287966,15274944,15276401,15277932,15277262,15269835,15273487,15263054,15272422,15273636,15266018,15265477,15269367,15280709,15275380,15261963,15271254,15277994,15287898,15280281,15276913,15262620,15282195,15266136,15260384,15270606,15282334,15263840,15261691,15262829,15284942,15270110,15287977,15262969,15289116,15264915,15279442,15272894,15260721,15282446,15281972,15281111,15281981,15292240,15261666,15261795,15265936,15284406,15268603,15262440,15281776,15271385,15278498,15270158,15271316,15259041,15267740,15266441,15266439,15264169,15279202,15265356,15261318,15269253,15269484,15285759,15265538,15286212,15285414,15260350,15278887,15285231,15286201,15280507,15266043,15270188,15265246,15285166,15268037,15284858,15277302,15289725,15278907,15261525,15282930,15284722,15285384,15271235,15260411,15260932,15283855,15278325,15278360,15258547,15277669,15269580,15284669,15265589,15270103,15279019,15262326,15284283,15264997,15263423,15271214,15262218,15258446,15269493,15281909,15269723,15265157,15276813,15277946,15268218,15272888,15280178,15267576,15272162,15265000,15274150,15283440,15262729,15267493,15259758,15260018,15268701,15265009,15268045,15269851,15272453,15285349,15285323,15270496,15264989,15271862,15277451,15275826,15269184,15285077,15273623,15261776,15267146,15273220,15270515,15262663,15263744,15267102,15264813,15264588,15268113,15282388,15267516,15280090,15270355,15278196,15269477,15270821,15267719,15262036,15269188,15288002,15278158,15270257,15261771,15271934,15280690,15258457,15264354,15267122,15263675,15267379,15272748,15276202,15270678,15266329,15262048,15272933,15266750,15272816,15264091,15275051,15259474,15272620,15282559,15259256,15259447,15278546,15280853,15280852,15282372,15266106,15279378,15267532,15280735,15262717,15265637,15288389,15271737,15276690,15265278,15280446,15265221,15269202,15274840,15281877,15274193,15279503,15262606,15279234,15266935,15260957,15281583,15278873,15268183,15264863,15271799,15276305,15263575,15260886,15277933,15272529,15286418,15273450,15261213,15263611,15269198,15285508,15272758,15283313,15288365,15267747,15272448,15262660,15279883,15260297,15272144,15267742,15271533,15261925,15272926,15263343,15269387,15267925,15265937,15270239,15263505,15277093,15273655,15264053,15271144,15267828,15263869,15267628,15262948,15268794,15259060,15261562,15261857,15275017,15267806,15267794,15271821,15260814,15271242,15264350,15278008,15292215,15261459,15264342,15267968,15263253,15258682,15259454,15264219,15271676,15267520,15269532,15261709,15271071,15260989,15270787,15261744,15280107]";

    private static final String SIMPLE_JSON_DATA_WITH_POJO = "{ \"id\": 1, \"name\": \"first\", \"story\": { \"title\": \"story1\", \"id\": 1001 } }";
    private static final String SIMPLE_JSON_DATA_WITH_LIST_POJO = "{ \"id\": 1, \"name\": \"first\", \"story\": { \"title\": \"story1\", \"id\": 1001 }, \"response\" : [ {\"name\":\"response2\", id : 2}, {\"name\":\"response3\", id : 3}, {\"name\":\"response4\", id : 4} ] }";

    private static final String SIMPLE_JSON_DATA_WITH_POJO_WRONG_TYPE = "{ \"id\": \"\", \"name\": \"first\", \"story\": { \"title\": \"story1\", \"id\": 1001 } }";

    private static final String TAG = "GsonActivity";

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Gson gson = new Gson();
        Log.v(TAG, String.valueOf(gson.toJson(gson.fromJson(HACKER_NEWS_STORY_JSON_ALIAS_ID, HackerNewStory.class)).equals(gson.toJson(gson.fromJson(HACKER_NEWS_STORY_JSON, HackerNewStory.class)))));
        Log.v(TAG, String.valueOf(gson.toJson(gson.fromJson(HACKER_NEWS_STORY_JSON_ALIAS_ID, HackerNewStoryNoSerial.class))));
        Log.v(TAG, String.valueOf(gson.toJson(gson.fromJson(HACKER_NEWS_STORY_JSON, HackerNewStoryNoSerial.class))));

        Type type = new TypeToken<List<Integer>>() {
        }.getType();
        List<Integer> items = gson.fromJson(HACKER_NEWS_TOP_STORIES_JSON, type);
        Log.v(TAG, "items size: " + items.size());

        Log.v(TAG, String.valueOf(gson.toJson(gson.fromJson(SIMPLE_JSON_DATA_WITH_POJO, SimpleUser.class))));
        Log.v(TAG, String.valueOf(gson.toJson(gson.fromJson(SIMPLE_JSON_DATA_WITH_LIST_POJO, SimpleUserWithResponse.class))));

        Gson gsonBuilder = new GsonBuilder()
                .serializeNulls()
                .excludeFieldsWithoutExposeAnnotation()
                .create();

        Log.v(TAG, gsonBuilder.toJson(gsonBuilder.fromJson(SIMPLE_JSON_DATA_WITH_POJO, SimpleUser.class)));

        SimpleUser simpleUser = new SimpleUser();
        simpleUser.mId = 10;
        simpleUser.mName = "name1";

        Log.v(TAG, gsonBuilder.toJson(simpleUser));

        Gson modifierSampleBuilder = new GsonBuilder().excludeFieldsWithModifiers(Modifier.PRIVATE).create();
        Log.v(TAG, gson.toJson(new ModifierSample()));
        Log.v(TAG, modifierSampleBuilder.toJson(new ModifierSample()));

        Gson namePolicyBuilder = new GsonBuilder()
                .setFieldNamingPolicy(FieldNamingPolicy.LOWER_CASE_WITH_DASHES)
                .create();
        Log.v(TAG, namePolicyBuilder.toJson(new ModifierSample()));

        Gson nameStrategyBuilder = new GsonBuilder()
                .setFieldNamingStrategy(new FieldNamingStrategy() {
                    @Override
                    public String translateName(Field f) {
                        return f.getName().replace("f", "rrr");
                    }
                })
                .create();
        Log.v(TAG, nameStrategyBuilder.toJson(new ModifierSample()));

        Gson typeAdapterBuilder = new GsonBuilder()
                .registerTypeAdapter(long.class, new TypeAdapter<Long>() {
                    @Override
                    public void write(JsonWriter out, Long value) throws IOException {
                        out.value(String.valueOf(value));
                    }

                    @Override
                    public Long read(JsonReader in) throws IOException {
                        try {
                            return Long.parseLong(in.nextString());
                        } catch (NumberFormatException e) {
                            return -1L;
                        }
                    }
                })
                .create();
        Log.v(TAG, typeAdapterBuilder.toJson(typeAdapterBuilder.fromJson(SIMPLE_JSON_DATA_WITH_POJO_WRONG_TYPE, SimpleUser.class)));

        Gson typeAdapterBuilder1 = new GsonBuilder()
                .registerTypeAdapter(long.class, new JsonDeserializer<Long>() {
                    @Override
                    public Long deserialize(JsonElement json, Type typeOfT, JsonDeserializationContext context) throws JsonParseException {
                        try {
                            return json.getAsLong();
                        } catch (NumberFormatException e) {
                            return -1L;
                        }
                    }
                })
                .create();
        Log.v(TAG, typeAdapterBuilder1.toJson(typeAdapterBuilder1.fromJson(SIMPLE_JSON_DATA_WITH_POJO_WRONG_TYPE, SimpleUser.class)));
    }
}
