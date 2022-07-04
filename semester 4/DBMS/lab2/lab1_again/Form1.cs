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
using System.Configuration;

namespace lab1_again
{
    public partial class Form1 : Form
    {
        //parent=CountryOfOrigin
        //child=Movie
        SqlConnection conn;
        DataSet ds; //stores data used in the application (table, rows, columns, constraints)
        //has RowState flag that determines whether a row is inserted, deleted, updated or unchanged
        SqlDataAdapter daParentTable, daChildTable; //empty until SqlDataAdapter queries are executed
        SqlCommandBuilder cbChildTable;
        BindingSource bsParentTable, bsChildTable; //binds controls on from to tables in dataset

        private void saveButton_Click(object sender, EventArgs e)
        {
            //changes values in database with every row inserted/deleted/updated
            //RowState property is examined to determin for which rows to apply InsertColumn/DeleteColumn/UpdateColumn
            string childTable = ConfigurationSettings.AppSettings["childTable"];
            daChildTable.Update(ds, childTable);
        }

        private void reloadButton_Click(object sender, EventArgs e)
        {
            //configuration file 
            string parentTable = ConfigurationSettings.AppSettings.Get("parentTable");
            string childTable = ConfigurationSettings.AppSettings.Get("childTable");
            string columnParentTable = ConfigurationSettings.AppSettings.Get("columnParentTable");
            string columnChildTable = ConfigurationSettings.AppSettings.Get("columnChildTable");
            string databaseName = ConfigurationSettings.AppSettings.Get("databaseName");

            //connect to database
            conn = new SqlConnection("Data Source = DESKTOP-C2O3RQA\\SQLEXPRESS; Initial Catalog=" + databaseName + ";" + "Integrated Security=SSPI;");
            ds = new DataSet();
            //read from country of origin table in database
            daParentTable = new SqlDataAdapter("select * from " + parentTable, conn);
            //read from movie table in database
            daChildTable = new SqlDataAdapter("select * from " + childTable, conn);
            cbChildTable = new SqlCommandBuilder(daChildTable);

            //adds rows to match those in database for movie table
            daChildTable.Fill(ds, childTable);
            //for country of origin table
            daParentTable.Fill(ds, parentTable);

            //makes a foreign key constraint in movie table
            DataRelation dr = new DataRelation("FK_" + childTable + "_" + parentTable, ds.Tables[parentTable].Columns[columnParentTable], ds.Tables[childTable].Columns[columnChildTable]);
            ds.Relations.Add(dr);

            //makes bridge between database data and form
            bsParentTable = new BindingSource();
            bsParentTable.DataSource = ds;
            bsParentTable.DataMember = parentTable;

            //data grid view for country of origin table will be populated with country of origin table from database
            dgv_parentTable.DataSource = bsParentTable;

            //same thing as above, only here we bind the foreig key so that the 2 tables will be bound together by the foreign key constraint
            bsChildTable = new BindingSource();
            bsChildTable.DataSource = bsParentTable;
            bsChildTable.DataMember = "FK_" + childTable + "_" + parentTable;

            dgv_childTable.DataSource = bsChildTable;
        }

        public Form1()
        {
            InitializeComponent();
        }

        private void Form1_Load(object sender, EventArgs e)
        {
            //configuration file 
            string parentTable = ConfigurationSettings.AppSettings.Get("parentTable");
            string childTable = ConfigurationSettings.AppSettings.Get("childTable");
            string columnParentTable = ConfigurationSettings.AppSettings.Get("columnParentTable");
            string columnChildTable = ConfigurationSettings.AppSettings.Get("columnChildTable");
            string databaseName = ConfigurationSettings.AppSettings.Get("databaseName");
            label1.Text = parentTable;
            label2.Text = childTable;

            //connect to database
            conn = new SqlConnection("Data Source = DESKTOP-C2O3RQA\\SQLEXPRESS; Initial Catalog=" + databaseName + ";" + "Integrated Security=SSPI;");
            ds = new DataSet();
            //read from country of origin table in database
            daParentTable = new SqlDataAdapter("select * from " + parentTable, conn);
            //read from movie table in database
            daChildTable = new SqlDataAdapter("select * from " + childTable, conn);
            cbChildTable = new SqlCommandBuilder(daChildTable);

            //adds rows to match those in database for movie table
            daChildTable.Fill(ds, childTable);
            //for country of origin table
            daParentTable.Fill(ds, parentTable);

            //makes a foreign key constraint in movie table
            DataRelation dr = new DataRelation("FK_" + childTable + "_" + parentTable, ds.Tables[parentTable].Columns[columnParentTable], ds.Tables[childTable].Columns[columnChildTable]);
            ds.Relations.Add(dr);

            //makes bridge between database data and form
            bsParentTable = new BindingSource();
            bsParentTable.DataSource = ds;
            bsParentTable.DataMember = parentTable;

            //data grid view for country of origin table will be populated with country of origin table from database
            dgv_parentTable.DataSource = bsParentTable;

            //same thing as above, only here we bind the foreig key so that the 2 tables will be bound together by the foreign key constraint
            bsChildTable = new BindingSource();
            bsChildTable.DataSource = bsParentTable;
            bsChildTable.DataMember = "FK_" + childTable + "_" + parentTable;

            dgv_childTable.DataSource = bsChildTable;
        }
    }
}
