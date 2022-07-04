from ppm import PPM
from encoder import Encoder, write_to_file
from decoder import Decoder


if __name__ == '__main__':
    ppm_file = PPM()
    input_file = input("input file name >>> ")
    ppm_file.read_file(input_file)
    ppm_file.convert_rgb_to_yuv()

    encoder = Encoder(ppm_file)
    encoder.make_y_block_list()
    encoder.make_uv_block_list()

    encoder.make_u_into_8x8()
    encoder.make_v_into_8x8()
    encoder.prepare_y()
    encoder.yuv_to_dct_blocks()
    encoder.quantization_phase()

    encoder.encode_output_array()

    decoder = Decoder(encoder)
    decoder.reconstruct_dct_from_encoded_output()

    decoder.dequantization_phase()
    decoder.dct_to_yuv()

    decoder.make_y_from_list()
    decoder.make_u_from_list()
    decoder.make_v_from_list()


    ppm_file.convert_yuv_to_rgb(decoder.y, decoder.u, decoder.v)
    output_file = input("output file name >>> ")
    ppm_file.write_to_file(output_file)