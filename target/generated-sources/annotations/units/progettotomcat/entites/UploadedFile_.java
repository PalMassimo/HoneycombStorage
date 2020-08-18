package units.progettotomcat.entites;

import java.util.Date;
import javax.annotation.Generated;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value = "org.hibernate.jpamodelgen.JPAMetaModelEntityProcessor")
@StaticMetamodel(UploadedFile.class)
public abstract class UploadedFile_ {

	public static volatile SingularAttribute<UploadedFile, Date> uploadDate;
	public static volatile SingularAttribute<UploadedFile, String[]> hashtags;
	public static volatile SingularAttribute<UploadedFile, Uploader> uploader;
	public static volatile SingularAttribute<UploadedFile, String> name;
	public static volatile SingularAttribute<UploadedFile, Long> id;
	public static volatile SingularAttribute<UploadedFile, String> addressIP;
	public static volatile SingularAttribute<UploadedFile, Date> seenDate;
	public static volatile SingularAttribute<UploadedFile, byte[]> content;

}

