package units.progettotomcat.entites;

import javax.annotation.Generated;
import javax.persistence.metamodel.ListAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value = "org.hibernate.jpamodelgen.JPAMetaModelEntityProcessor")
@StaticMetamodel(Uploader.class)
public abstract class Uploader_ extends units.progettotomcat.entites.Utente_ {

	public static volatile ListAttribute<Uploader, UploadedFile> uploadedFiles;
	public static volatile SingularAttribute<Uploader, byte[]> logo;
	public static volatile ListAttribute<Uploader, Consumer> consumers;

}

