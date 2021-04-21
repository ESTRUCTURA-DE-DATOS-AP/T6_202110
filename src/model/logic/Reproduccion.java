package model.logic;

public class Reproduccion implements Comparable<Reproduccion>
{

	private double instrumentalness;
	private double liveness ;
	private double speechiness ;
	private double danceability;
	private double valence;
	private double loudness;
	private double tempo;
	private double acousticness;
	private double energy;
	private double mode;
	private double key;
	private String artist_id;
	private String tweet_lang;
	private String track_id ;
	private String created_at;
	private String lang;
	private String time_zone;
	private String user_id;
	private String id;
	
	
	public Reproduccion(double pInstrumentalness, double pLiveness, double pSpeechiness, double pDanceability,double pValence, double pLoudness, double pTempo,
			double pAcousticness, double pEnergy, double pMode, double pKey,String pArtist_id, String pTweet_lang, String pTrack_id, String pCreated_at, String pLang, String pTime_zone,String pUser_id, String pId) 
	{
		instrumentalness = pInstrumentalness;
		liveness = pLiveness;
		speechiness =pSpeechiness;
		danceability=pDanceability;
		valence=pValence;
		loudness=pLoudness;
		tempo=pTempo;
		acousticness= pAcousticness;
		energy=pEnergy;
		mode = pMode;
		key = pKey;
		artist_id = pArtist_id;
		tweet_lang = pTweet_lang;
		track_id = pTrack_id;
		created_at = pCreated_at;
		lang = pLang;
		time_zone = pTime_zone;
		user_id = pUser_id;
		id = pId;
	}


	@Override
	public int compareTo(Reproduccion o) {
		// TODO Auto-generated method stub
		return 0;
	}

}
