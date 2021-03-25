using System;
using System.Collections.Generic;
using System.ComponentModel;
using System.Data;
using System.Drawing;
using System.Linq;
using System.Text;
using System.Threading.Tasks;
using System.Windows.Forms;
using System.Data.SqlClient;

namespace lab1_again
{
    public partial class Form1 : Form
    {
        //parent=CountryOfOrigin
        //child=Movie
        SqlConnection conn;
        DataSet ds;
        SqlDataAdapter daCountryOfOrigin, daMovie;
        SqlCommandBuilder cbMovie;
        BindingSource bsCountryOfOrigin, bsMovie;

        private void saveButton_Click(object sender, EventArgs e)
        {
            daMovie.Update(ds, "Movie");
        }

        private void reloadButton_Click(object sender, EventArgs e)
        {
            conn = new SqlConnection("Data Source = DESKTOP-C2O3RQA\\SQLEXPRESS; Initial Catalog=Cinema;" + "Integrated Security=SSPI;");
            ds = new DataSet();
            daCountryOfOrigin = new SqlDataAdapter("select * from CountryOfOrigin", conn);
            daMovie = new SqlDataAdapter("select * from Movie", conn);
            cbMovie = new SqlCommandBuilder(daMovie);

            daMovie.Fill(ds, "Movie");
            daCountryOfOrigin.Fill(ds, "CountryOfOrigin");

            DataRelation dr = new DataRelation("FK_Movie_CountryOfOrigin", ds.Tables["CountryOfOrigin"].Columns["id"], ds.Tables["Movie"].Columns["idCountryOfOrigin"]);
            ds.Relations.Add(dr);

            bsCountryOfOrigin = new BindingSource();
            bsCountryOfOrigin.DataSource = ds;
            bsCountryOfOrigin.DataMember = "CountryOfOrigin";

            dgv_CountryOfOrigin.DataSource = bsCountryOfOrigin;

            bsMovie = new BindingSource();
            bsMovie.DataSource = bsCountryOfOrigin;
            bsMovie.DataMember = "FK_Movie_CountryOfOrigin";

            dgv_Movie.DataSource = bsMovie;
        }

        public Form1()
        {
            InitializeComponent();
        }

        private void Form1_Load(object sender, EventArgs e)
        {
            conn = new SqlConnection("Data Source = DESKTOP-C2O3RQA\\SQLEXPRESS; Initial Catalog=Cinema;" + "Integrated Security=SSPI;");
            ds = new DataSet();
            daCountryOfOrigin = new SqlDataAdapter("select * from CountryOfOrigin", conn);
            daMovie = new SqlDataAdapter("select * from Movie", conn);
            cbMovie = new SqlCommandBuilder(daMovie);

            daMovie.Fill(ds, "Movie");
            daCountryOfOrigin.Fill(ds, "CountryOfOrigin");

            DataRelation dr = new DataRelation("FK_Movie_CountryOfOrigin", ds.Tables["CountryOfOrigin"].Columns["id"], ds.Tables["Movie"].Columns["idCountryOfOrigin"]);
            ds.Relations.Add(dr);

            bsCountryOfOrigin = new BindingSource();
            bsCountryOfOrigin.DataSource = ds;
            bsCountryOfOrigin.DataMember = "CountryOfOrigin";

            dgv_CountryOfOrigin.DataSource = bsCountryOfOrigin;

            bsMovie = new BindingSource();
            bsMovie.DataSource = bsCountryOfOrigin;
            bsMovie.DataMember = "FK_Movie_CountryOfOrigin";

            dgv_Movie.DataSource = bsMovie;
        }
    }
}
