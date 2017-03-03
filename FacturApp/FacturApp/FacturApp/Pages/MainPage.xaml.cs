using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using System.Threading.Tasks;

using Xamarin.Forms;
using Xamarin.Forms.Xaml;

namespace FacturApp.Pages
{
    [XamlCompilation(XamlCompilationOptions.Compile)]
    public partial class MainPage : ContentPage
    {
        public MainPage()
        {
            InitializeComponent();
        }

        private void btn_clientes_Clicked(object sender, EventArgs e)
        {
            Navigation.PushAsync(new Clientes());
        }

        private void btn_plantillas_Clicked(object sender, EventArgs e)
        {
            Navigation.PushAsync(new Plantillas());
        }
    }
}
