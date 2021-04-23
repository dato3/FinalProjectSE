package DataBeans;
import java.util.Date;

import org.genericdao.PrimaryKey;

@PrimaryKey("periodicalName") 
public class Periodical {
    private String periodicalName;
    private String title;
    private String image;
    private Date postDate;
    private String availability;

    public String 	getPeriodicalName() { return periodicalName; 	}
    public String	getTitle()			{ return title;				}
    public String 	getImage()       	{ return image; 			}
    public Date 	getPostDate()		{ return postDate;			}
    public String 	getAvailability() 	{ return availability; 		}

    public void setPeriodicalName(String s)  	{ periodicalName = s;    	}
    public void setTitle(String s)				{ title = s;				}
    public void setImage(String s)  			{ image = s;    			}
    public void setPostDate(Date d)				{ postDate = d;				}
    public void setAvailability(String s)		{ availability = s;			}
}	