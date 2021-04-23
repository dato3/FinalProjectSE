package DataBeans;

import java.util.Date;

import org.genericdao.PrimaryKey;

@PrimaryKey("userName") 
public class Client {
    private String userName;
    private String position;
    private String password;
    private String firstName;
    private String lastName;
    private Date dateOfBirth;
    private int mailCode;
    private String phoneNumber;
    private String image;

    public String 	getUserName()       { return userName; 		}
    public String	getPosition()		{ return position;		}
    public String 	getPassword()       { return password; 		}
    public String 	getFirstName()      { return firstName;		}
    public String 	getLastName()     	{ return lastName; 		}
    public Date		getDateOfBirth() 	{ return dateOfBirth;	}
    public int 		getMailCode()		{ return mailCode;		}
    public String 	getPhoneNumber()	{ return phoneNumber;	}
    public String 	getImage()			{ return image;  		}

    public void setUserName(String s)  	{ userName = s;    	}
    public void setPosition(String s)	{ position = s;		}
    public void setPassword(String s)  	{ password = s;    	}
    public void setFirstName(String s)  { firstName = s;    }
    public void setLastName(String s)  	{ lastName = s;    	}
    public void setDateOfBirth(Date d)	{ dateOfBirth = d;	}
    public void setMailCode(int m)		{ mailCode = m;		}
    public void setPhoneNumber(String p){ phoneNumber = p;	}
    public void setImage(String i)		{ image = i;		}
}