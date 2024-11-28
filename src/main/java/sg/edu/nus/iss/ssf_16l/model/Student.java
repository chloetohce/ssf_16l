package sg.edu.nus.iss.ssf_16l.model;

public class Student {
    private Integer id;
    private String name;
    private String email;
    private String phoneNo;

    public Student(Integer id, String name, String email, String phoneNo) {
        this.id = id;
        this.name = name;
        this.email = email;
        this.phoneNo = phoneNo;
    }

    public Student() {
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((id == null) ? 0 : id.hashCode());
        result = prime * result + ((name == null) ? 0 : name.hashCode());
        result = prime * result + ((email == null) ? 0 : email.hashCode());
        result = prime * result + ((phoneNo == null) ? 0 : phoneNo.hashCode());
        return result;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (obj == null)
            return false;
        if (getClass() != obj.getClass())
            return false;
        Student other = (Student) obj;

        if ((id == null) || (name == null) || (email == null) || (phoneNo == null)) {
            return false;
        }
        
        return id.equals(other.id) && name.equals(other.name) && email.equals(other.email) && phoneNo.equals(other.phoneNo);
    }

    public int getId() {return id;}
    public void setId(int id) {this.id = id;}

    public String getName() {return name;}
    public void setName(String name) {this.name = name;}

    public String getEmail() {return email;}
    public void setEmail(String email) {this.email = email;}

    public String getPhoneNo() {return phoneNo;}
    public void setPhoneNo(String phoneNo) {this.phoneNo = phoneNo;}

    @Override
    public String toString() {
        return String.format("%s,%s,%s,%s", id, name, email, phoneNo);
    }
}
