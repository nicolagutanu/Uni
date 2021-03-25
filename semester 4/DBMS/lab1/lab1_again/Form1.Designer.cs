
namespace lab1_again
{
    partial class Form1
    {
        /// <summary>
        /// Required designer variable.
        /// </summary>
        private System.ComponentModel.IContainer components = null;

        /// <summary>
        /// Clean up any resources being used.
        /// </summary>
        /// <param name="disposing">true if managed resources should be disposed; otherwise, false.</param>
        protected override void Dispose(bool disposing)
        {
            if (disposing && (components != null))
            {
                components.Dispose();
            }
            base.Dispose(disposing);
        }

        #region Windows Form Designer generated code

        /// <summary>
        /// Required method for Designer support - do not modify
        /// the contents of this method with the code editor.
        /// </summary>
        private void InitializeComponent()
        {
            this.label1 = new System.Windows.Forms.Label();
            this.label2 = new System.Windows.Forms.Label();
            this.dgv_CountryOfOrigin = new System.Windows.Forms.DataGridView();
            this.dgv_Movie = new System.Windows.Forms.DataGridView();
            this.saveButton = new System.Windows.Forms.Button();
            this.reloadButton = new System.Windows.Forms.Button();
            ((System.ComponentModel.ISupportInitialize)(this.dgv_CountryOfOrigin)).BeginInit();
            ((System.ComponentModel.ISupportInitialize)(this.dgv_Movie)).BeginInit();
            this.SuspendLayout();
            // 
            // label1
            // 
            this.label1.AutoSize = true;
            this.label1.Location = new System.Drawing.Point(37, 34);
            this.label1.Name = "label1";
            this.label1.Size = new System.Drawing.Size(118, 17);
            this.label1.TabIndex = 0;
            this.label1.Text = "Country Of Origin";
            // 
            // label2
            // 
            this.label2.AutoSize = true;
            this.label2.Location = new System.Drawing.Point(40, 227);
            this.label2.Name = "label2";
            this.label2.Size = new System.Drawing.Size(45, 17);
            this.label2.TabIndex = 1;
            this.label2.Text = "Movie";
            // 
            // dgv_CountryOfOrigin
            // 
            this.dgv_CountryOfOrigin.ColumnHeadersHeightSizeMode = System.Windows.Forms.DataGridViewColumnHeadersHeightSizeMode.AutoSize;
            this.dgv_CountryOfOrigin.Location = new System.Drawing.Point(40, 54);
            this.dgv_CountryOfOrigin.Name = "dgv_CountryOfOrigin";
            this.dgv_CountryOfOrigin.RowHeadersWidth = 51;
            this.dgv_CountryOfOrigin.RowTemplate.Height = 24;
            this.dgv_CountryOfOrigin.Size = new System.Drawing.Size(689, 150);
            this.dgv_CountryOfOrigin.TabIndex = 2;
            // 
            // dgv_Movie
            // 
            this.dgv_Movie.ColumnHeadersHeightSizeMode = System.Windows.Forms.DataGridViewColumnHeadersHeightSizeMode.AutoSize;
            this.dgv_Movie.Location = new System.Drawing.Point(40, 260);
            this.dgv_Movie.Name = "dgv_Movie";
            this.dgv_Movie.RowHeadersWidth = 51;
            this.dgv_Movie.RowTemplate.Height = 24;
            this.dgv_Movie.Size = new System.Drawing.Size(689, 150);
            this.dgv_Movie.TabIndex = 3;
            // 
            // saveButton
            // 
            this.saveButton.Location = new System.Drawing.Point(788, 110);
            this.saveButton.Name = "saveButton";
            this.saveButton.Size = new System.Drawing.Size(134, 48);
            this.saveButton.TabIndex = 4;
            this.saveButton.Text = "Save data";
            this.saveButton.UseVisualStyleBackColor = true;
            this.saveButton.Click += new System.EventHandler(this.saveButton_Click);
            // 
            // reloadButton
            // 
            this.reloadButton.Location = new System.Drawing.Point(788, 314);
            this.reloadButton.Name = "reloadButton";
            this.reloadButton.Size = new System.Drawing.Size(134, 44);
            this.reloadButton.TabIndex = 5;
            this.reloadButton.Text = "Reload Data";
            this.reloadButton.UseVisualStyleBackColor = true;
            this.reloadButton.Click += new System.EventHandler(this.reloadButton_Click);
            // 
            // Form1
            // 
            this.AutoScaleDimensions = new System.Drawing.SizeF(8F, 16F);
            this.AutoScaleMode = System.Windows.Forms.AutoScaleMode.Font;
            this.ClientSize = new System.Drawing.Size(1010, 450);
            this.Controls.Add(this.reloadButton);
            this.Controls.Add(this.saveButton);
            this.Controls.Add(this.dgv_Movie);
            this.Controls.Add(this.dgv_CountryOfOrigin);
            this.Controls.Add(this.label2);
            this.Controls.Add(this.label1);
            this.Name = "Form1";
            this.Text = "Form1";
            this.Load += new System.EventHandler(this.Form1_Load);
            ((System.ComponentModel.ISupportInitialize)(this.dgv_CountryOfOrigin)).EndInit();
            ((System.ComponentModel.ISupportInitialize)(this.dgv_Movie)).EndInit();
            this.ResumeLayout(false);
            this.PerformLayout();

        }

        #endregion

        private System.Windows.Forms.Label label1;
        private System.Windows.Forms.Label label2;
        private System.Windows.Forms.DataGridView dgv_CountryOfOrigin;
        private System.Windows.Forms.DataGridView dgv_Movie;
        private System.Windows.Forms.Button saveButton;
        private System.Windows.Forms.Button reloadButton;
    }
}

