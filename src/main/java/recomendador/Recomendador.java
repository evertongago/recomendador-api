package recomendador;

import java.io.IOException;
import java.util.List;

import org.apache.mahout.cf.taste.common.TasteException;
import org.apache.mahout.cf.taste.impl.neighborhood.NearestNUserNeighborhood;
import org.apache.mahout.cf.taste.impl.recommender.GenericBooleanPrefItemBasedRecommender;
import org.apache.mahout.cf.taste.impl.recommender.GenericUserBasedRecommender;
import org.apache.mahout.cf.taste.impl.similarity.PearsonCorrelationSimilarity;
import org.apache.mahout.cf.taste.impl.similarity.TanimotoCoefficientSimilarity;
import org.apache.mahout.cf.taste.model.DataModel;
import org.apache.mahout.cf.taste.neighborhood.UserNeighborhood;
import org.apache.mahout.cf.taste.recommender.RecommendedItem;
import org.apache.mahout.cf.taste.similarity.ItemSimilarity;
import org.apache.mahout.cf.taste.similarity.UserSimilarity;

public class Recomendador {

	public void userBased() throws IOException, TasteException {
		long userId = 1;
		int vizinhos = 10;
		int totalRecomendacoes = 5;
		
		DataModel dataModel = FileManager.getFileDataModel();
		UserSimilarity similarity = new PearsonCorrelationSimilarity(dataModel);
		UserNeighborhood neighborhood = new NearestNUserNeighborhood(vizinhos, similarity, dataModel);
		GenericUserBasedRecommender recommender = new GenericUserBasedRecommender(dataModel, neighborhood, similarity);
		List<RecommendedItem> recommendations = recommender.recommend(userId, totalRecomendacoes);
		for (RecommendedItem item: recommendations) {
			System.out.println(item);
		}
	}
	
	public void itemBased() throws IOException, TasteException {
		long userId = 21;
		int totalRecomendacoes = 5;
		
		DataModel dataModel = FileManager.getFileDataModel();
		ItemSimilarity similarity = new TanimotoCoefficientSimilarity(dataModel);
		GenericBooleanPrefItemBasedRecommender itemRecommender = new GenericBooleanPrefItemBasedRecommender(dataModel, similarity);
		List<RecommendedItem> recommendations = itemRecommender.recommend(userId, totalRecomendacoes);
		for (RecommendedItem item: recommendations) {
			System.out.println(item);
		}
	}
	
	public static void main(String[] args) throws IOException, TasteException {
		Recomendador recomendador = new Recomendador();
		recomendador.userBased();
		recomendador.itemBased();
	}
	
}