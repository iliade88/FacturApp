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
            var tgr = new TapGestureRecognizer();
            tgr.Tapped += image_Clicked;
            this.imagen.GestureRecognizers.Add(tgr);
        }

        private void btn_clientes_Clicked(object sender, EventArgs e)
        {
            Navigation.PushAsync(new Clientes());
        }

        private void btn_plantillas_Clicked(object sender, EventArgs e)
        {
            Navigation.PushAsync(new Plantillas());
        }

        private void image_Clicked(object sender, EventArgs e)
        {
            Navigation.PushAsync(new EditarPerfil());
        }
    }
}
