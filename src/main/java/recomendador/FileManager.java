package recomendador;

import java.io.File;
import java.io.IOException;

import org.apache.mahout.cf.taste.impl.model.file.FileDataModel;
import org.apache.mahout.cf.taste.model.DataModel;

public class FileManager {

	public static DataModel getFileDataModel() throws IOException {
		File file = new File("/home/everton.gago/projetos/acessos.data");
		return new FileDataModel(file);
	}
	
}