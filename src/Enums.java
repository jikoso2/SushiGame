import java.util.Arrays;
import java.util.List;

public class Enums {

    enum EComponent {
        Avocado,ButterFish,Caviar,Kanpyo,Kappa,Nori,Oshinko,Rice,Salmon,Shrimp,Taco,Tuna;
    }

    enum ESushi {

        // LIST of Sushi rolls:

        KappaMaki("Kappa Maki", EComponent.Kappa, EComponent.Rice, EComponent.Nori),

        VegeFutomaki("Vege Futomaki", EComponent.Kappa, EComponent.Rice, EComponent.Avocado, EComponent.Oshinko,
                EComponent.Nori, EComponent.Kanpyo),

        GunkanCaviar("Gunkan Caviar", EComponent.Caviar, EComponent.Rice, EComponent.Nori);


        private String name;
        private List<EComponent> componentsList;

        private ESushi(String name, EComponent... components) {
            this.name = name;
            this.componentsList = Arrays.asList(components);
        }

        public String getName() {
            return name;
        }

        public List<EComponent> getComponentList(){
            return componentsList;
        }
    }
}
