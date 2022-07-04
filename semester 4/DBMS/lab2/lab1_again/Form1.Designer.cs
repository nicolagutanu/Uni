using System.Configuration;

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
            this.dgv_parentTable = new System.Windows.Forms.DataGridView();
            this.dgv_childTable = new System.Windows.Forms.DataGridView();
            this.saveButton = new System.Windows.Forms.Button();
            this.reloadButton = new System.Windows.Forms.Button();
            ((System.ComponentModel.ISupportInitialize)(this.dgv_parentTable)).BeginInit();
            ((System.ComponentModel.ISupportInitialize)(this.dgv_childTable)).BeginInit();
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
            // dgv_parentTable
            // 
            this.dgv_parentTable.ColumnHeadersHeightSizeMode = System.Windows.Forms.DataGridViewColumnHeadersHeightSizeMode.AutoSize;
            this.dgv_parentTable.Location = new System.Drawing.Point(40, 54);
            this.dgv_parentTable.Name = "dgv_parentTable";
            this.dgv_parentTable.RowHeadersWidth = 51;
            this.dgv_parentTable.RowTemplate.Height = 24;
            this.dgv_parentTable.Size = new System.Drawing.Size(689, 150);
            this.dgv_parentTable.TabIndex = 2;
            // 
            // dgv_childTable
            // 
            this.dgv_childTable.ColumnHeadersHeightSizeMode = System.Windows.Forms.DataGridViewColumnHeadersHeightSizeMode.AutoSize;
            this.dgv_childTable.Location = new System.Drawing.Point(40, 260);
            this.dgv_childTable.Name = "dgv_childTable";
            this.dgv_childTable.RowHeadersWidth = 51;
            this.dgv_childTable.RowTemplate.Height = 24;
            this.dgv_childTable.Size = new System.Drawing.Size(689, 150);
            this.dgv_childTable.TabIndex = 3;
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
            this.Controls.Add(this.dgv_childTable);
            this.Controls.Add(this.dgv_parentTable);
            this.Controls.Add(this.label2);
            this.Controls.Add(this.label1);
            this.Name = "Form1";
            this.Text = "Form1";
            this.Load += new System.EventHandler(this.Form1_Load);
            ((System.ComponentModel.ISupportInitialize)(this.dgv_parentTable)).EndInit();
            ((System.ComponentModel.ISupportInitialize)(this.dgv_childTable)).EndInit();
            this.ResumeLayout(false);
            this.PerformLayout();

        }

        #endregion
        private System.Windows.Forms.Label label1;
        private System.Windows.Forms.Label label2;
        private System.Windows.Forms.DataGridView dgv_parentTable;
        private System.Windows.Forms.DataGridView dgv_childTable;
        private System.Windows.Forms.Button saveButton;
        private System.Windows.Forms.Button reloadButton;
    }
}

